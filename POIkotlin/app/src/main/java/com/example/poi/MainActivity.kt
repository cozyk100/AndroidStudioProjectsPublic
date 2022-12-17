package com.example.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.poi.databinding.ActivityMainBinding
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
        val xlsx = File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "sample.xlsx")
        Log.d("", xlsx.absolutePath)
        val str = buildString {
            WorkbookFactory.create(xlsx).use { wb ->
                val sheet = wb.getSheet("Sheet1")
                for (r in 0..sheet.lastRowNum) {
                    val row = sheet.getRow(r)
                    for (c in 0..row.lastCellNum -1) {
                        append(row.getCell(c))
                        append(" ")
                    }
                    append("\r\n")
                }
            }
        }
        binding.excelText.text = str
    }
}