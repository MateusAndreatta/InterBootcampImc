package com.mateusandreatta.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonGoToForm.setOnClickListener(::gotoForm)
    }

    fun gotoForm(view: View){
        startActivity(Intent(this, FormActivity::class.java))
    }
}