package com.example.volley

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.Volley
import org.apache.http.HttpEntity
import org.apache.http.entity.ContentType
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.mime.content.StringBody
import java.io.ByteArrayOutputStream
import java.io.File

/**
 * VolleyでHTTP POSTでMultipartで送信するサンプル
 */
class HttpPostMultiPart(private val context: Context) {

    companion object {
        const val TAG = "HttpPost"
    }

    /** VolleyのQueue */
    private var mQueue: RequestQueue = Volley.newRequestQueue(context)

    /**
     * アップロード
     * @param[url] Method=POSTで送信するアップロードphpのurl
     * @param[fileMap] Multipartのファイルの部分
     * @param[stringMap] Multipartのテキストの部分
     */
    fun doUpload(url: String, fileMap: Map<String, File>, stringMap: Map<String, String>) {
        val multipartRequest = MultipartRequest(
            url,
            // Response.Listener、Upload成功
            { response ->
                Log.d(TAG, "アップロード成功:\n $response")
                Toast.makeText(context, "ファイルのアップロードが完了しました。:${url}", Toast.LENGTH_LONG).show()
            },
            // Response.ErrorListener、Upload失敗
            { e ->
                Log.d(TAG, "アップロード失敗:\n ${e.networkResponse}", e)
                Toast.makeText(context, "ファイルのアップロードに失敗しました。:${url}", Toast.LENGTH_LONG).show()
            },
            stringMap,
            fileMap
        )
        mQueue.add(multipartRequest)
    }

    /**
     * Multipartのリクエストクラス
     * @constructor
     * @param[url] URL
     * @param[mListener] Upload成功時のリスナ
     * @param[errorListener] Upload失敗時のリスナ
     * @param[stringParts] MultiPartのstringパート
     * @param[fileParts] MultiPartのfileパート
     */
    class MultipartRequest(
        url: String,
        private val mListener: Response.Listener<String>,
        errorListener: Response.ErrorListener,
        private val stringParts: Map<String, String>,
        private val fileParts: Map<String, File>
    ) : Request<String?>(Method.POST, url, errorListener) {
        private lateinit var httpEntity: HttpEntity
        private var entity = MultipartEntityBuilder.create()
            .setCharset(charset("Shift_JIS"))

        /**
         * 初期化
         */
        init {
            buildMultipartEntity()
            // Connection timeout, Read Timeout
            retryPolicy =
                DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        }

        /**
         * 送信するリクエストを設定する
         */
        private fun buildMultipartEntity() {
            //File Data
            val contentType = ContentType.create("text/plain", charset("Shift_JIS"))
            fileParts.forEach { (k, v) ->
                entity.addBinaryBody(k, v, contentType, v.name)
            }
            stringParts.forEach { (k, v) ->
                entity.addPart(k, StringBody(v, contentType))
            }
            httpEntity = entity.build()
        }

        /**
         * POST または PUT 本文のコンテンツ タイプを返します
         * @return コンテンツ タイプ
         */
        override fun getBodyContentType(): String {
            return httpEntity.contentType?.value ?: ""
        }

        /**
         * 送信する生の POST または PUT 本文を返します。
         * デフォルトでは、本文は applicationx-www-form-urlencoded 形式のリクエスト パラメータで構成されます。
         * このメソッドをオーバーライドする場合は、getBodyContentType() もオーバーライドして、
         * 新しい本文形式に一致させることを検討してください。
         * @return レスポンスbody
         */
        override fun getBody(): ByteArray {
            // TODO ファイルのサイズが大きくて、OutOfMemoryが起きる場合はHurlStackの実装が必要
            // @see http://fly1tkg.github.io/2014/03/volley-multipart-form-data/
            val bos = ByteArrayOutputStream()
            httpEntity.writeTo(bos)
            return bos.toByteArray()
        }

        /**
         * サブクラスはこれを実装して、生のネットワーク応答を解析し、適切な応答タイプを返す必要があります。
         * このメソッドは、ワーカー スレッドから呼び出されます。 null を返すと、応答は配信されません。
         * @param[response] レスポンス(ヘダー、body含む)
         * @return パースされたレスポンス
         */
        override fun parseNetworkResponse(response: NetworkResponse?): Response<String?>? {
            return Response.success("Uploaded", cacheEntry)
        }

        /**
         * サブクラスはこれを実装して、解析された応答をリスナーに配信する必要があります。
         * 指定された応答は null でないことが保証されます。解析に失敗した応答は配信されません。
         * @param[response] レスポンス
         */
        override fun deliverResponse(response: String?) {
            //リスナーにレスポンスを返す
            mListener.onResponse(response)
        }
    }
}
