package com.abhishek.dunzoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhishek.dunzoapp.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            btnLoginContinue.isEnabled = isChecked
        }

        btnLoginContinue.setOnClickListener {
            if (etMobileNum.text.length<10){
                etMobileNum.error = "Invalid Number"

            }else{
                val intent = Intent(this@LoginActivity, VerifyMobileActivity::class.java)
                intent.putExtra("phoneNumber",etMobileNum.text.toString())
                startActivity(intent)
            }
        }
    }
}