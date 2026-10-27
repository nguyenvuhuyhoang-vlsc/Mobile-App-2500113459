package com.example.mobileapp_2500113459

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bắt buộc: Ánh xạ View bằng findViewById
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Kiểm tra bỏ trống Email hoặc Mật khẩu
            if (email.isEmpty() || password.isEmpty()) {
                tvStatus.setTextColor(Color.parseColor("#D32F2F"))
                tvStatus.text = "Email và Mật khẩu không được để trống!"
                return@setOnClickListener
            }

            // Kiểm tra định dạng Email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tvStatus.setTextColor(Color.parseColor("#D32F2F"))
                tvStatus.text = "Email không đúng định dạng!"
                return@setOnClickListener
            }

            // Kiểm tra độ dài Mật khẩu >= 6 ký tự
            if (password.length < 6) {
                tvStatus.setTextColor(Color.parseColor("#D32F2F"))
                tvStatus.text = "Mật khẩu phải từ 6 ký tự trở lên!"
                return@setOnClickListener
            }

            // Dữ liệu hợp lệ
            tvStatus.setTextColor(Color.parseColor("#388E3C"))
            tvStatus.text = "Đăng nhập hợp lệ"

            // Chuyển sang ProfileActivity bằng Explicit Intent và truyền Email
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("EXTRA_EMAIL", email)
            }
            startActivity(intent)
        }
    }
}
