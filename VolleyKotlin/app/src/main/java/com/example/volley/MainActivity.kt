package com.example.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.example.volley.databinding.ActivityMainBinding
import java.io.File

/**
 * VolleyでHTTP POSTでMultipartで送信するサンプル
 */
class MainActivity : AppCompatActivity() {
    companion object {
        // TODO URLは適当に変える
        const val URL = "http://192.168.0.1:8080/fileUpload.php"
    }

    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    /**
     * アクティビティの初期化処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{
            setContentView(this.root)
        }
        // アップロードするファイル(例)
        val upFile = File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "upFile.txt")

        binding.sendBtn.setOnClickListener {
            val fileContent = binding.fileContent.text.toString()
            upFile.writeText(fileContent)
            val textContent = binding.textContent.text.toString()
            // Multipartのファイルの部分
            val filePart = mapOf("upFile.txt" to  upFile)
            // Multipartのテキストの部分
            val stringPart = mapOf("text1" to textContent)
            val httpMultiPart = HttpPostMultiPart(applicationContext)
            httpMultiPart.doUpload(URL, filePart, stringPart)
        }
    }
}