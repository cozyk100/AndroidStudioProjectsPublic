package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        /** リクエストコード(値はなんでもいい) */
        private const val REQUEST_CODE_PERMISSIONS = 10
        /** リクエストするパーミッション */
        private val REQUIRED_PERMISSIONS =
            arrayOf (
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_BACKGROUND_LOCATION // TODO これがあると期待通り動かない
            )
    }
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    /**
     * Activityの初期化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        // パーミッションのリクエスト
        if (allPermissionsGranted()) {
            Toast.makeText(this,"既にパーミッションが許可されています", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }

    /** 全てのパーミッションｋが許可されているかのチェック */
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * パーミッションを要求した結果のコールバック。このメソッドは、requestPermissions の呼び出しごとに呼び出されます。
     * @param[requestCode] requestPermissions で渡されたリクエスト コード
     * @param[permissions] リクエストされたパーミッション
     * @param[grantResults] PERMISSION_GRANTED または PERMISSION_DENIED のいずれかである、対応するアクセス許可の付与結果。
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                Toast.makeText(this,"パーミッションが許可されました。", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"パーミッションが許可されませんでした。(-_-;)", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}