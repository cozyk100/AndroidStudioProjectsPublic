package jp.co.suzuken.datetimepickerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import jp.co.suzuken.datetimepickerkotlin.databinding.ActivityMainBinding

/**
 * メインアクティビティ
 */
class MainActivity : AppCompatActivity() {
    /** ViewBinding */
    private lateinit var binding: ActivityMainBinding

    /**
     * アクティビティの初期化
     * @param[savedInstanceState] Bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        // フラグメントへ
        supportFragmentManager.commit {
            addToBackStack(null)
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, MainFragment())
        }
    }
}