package com.example.intent_ptb2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnMain:Button
    lateinit var a:EditText
    lateinit var b:EditText
    lateinit var c:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        control()
        event()
    }

    private fun event() {
        btnMain.setOnClickListener {
            val douba = a.text.toString().toIntOrNull()
            val doubb = b.text.toString().toIntOrNull()
            val doubc = c.text.toString().toIntOrNull()

            if(douba == null|| doubb == null || doubc == null){
                Toast.makeText(this, "Nhap a, b, c", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var result = solve(douba, doubb, doubc)

            var intent = Intent(applicationContext, ResultScreen::class.java)
            intent.putExtra("MSG_MAINACTIVITY",result)
            intent.putExtra("a",douba)
            intent.putExtra("b",doubb)
            intent.putExtra("c",doubc)

            startActivity(intent)
        }
    }

    private fun solve(a: Int, b:Int, c:Int):String{
        return when {
            a==0 && b==0 && c==0 -> "Phuong trinh vo so nghiem"
            a==0 && b==0 -> "Phuong trinh vo nghiem"
            a==0 -> "Phuong trinh co nghiem duy nhat: x=${-c/b}"
            else -> {
                val delta = (b*b-4*a*c).toDouble()
                when {
                    delta > 0 -> {
                        val x1 = (-b+Math.sqrt(delta))/(2*a)
                        val x2 = (-b-Math.sqrt(delta))/(2*a)
                        "Phuong trinh co 2 nghiem phan biet: x1=${x1} va x2=${x2}"
                    }
                    delta == 0.0 -> {
                        val x = -b/(2*a)
                        "Phuong trinh co nghiem kep: x1=x2=${x}"
                    }
                    else -> "Phuong trinh vo nghiem"
                }
            }
        }
    }

    private fun control() {
        btnMain = findViewById(R.id.btnMain)
        a = findViewById(R.id.num1)
        b = findViewById(R.id.num2)
        c = findViewById(R.id.num3)
    }
}