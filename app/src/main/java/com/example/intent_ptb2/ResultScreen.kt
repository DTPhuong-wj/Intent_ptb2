package com.example.intent_ptb2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultScreen : AppCompatActivity() {

    lateinit var btnBack:Button
    lateinit var rs:TextView
    lateinit var result:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        control()
        event()
        var msg = intent.getStringExtra("MSG_MAINACTIVITY")
        val a = intent.getIntExtra("a", 0)
        val b = intent.getIntExtra("b", 0)
        val c = intent.getIntExtra("c", 0)
        rs.text = msg
        val title = buildString {
            append("${a}x²")
            if (b > 0) append(" + ${b}x") else if (b < 0) append(" - ${-b}x")
            if (c > 0) append(" + $c") else if (c < 0) append(" - ${-c}")
        }
        result.text = "Kết quả của phương trình: $title"
    }

    private fun event() {
        btnBack.setOnClickListener {
            var resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun control() {
        btnBack = findViewById(R.id.btnBack)
        rs = findViewById(R.id.rs)
        result = findViewById(R.id.result)
    }
}