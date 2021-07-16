package com.mateusandreatta.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val float = intent.extras?.get("imc")
        val status = intent.extras?.get("status")
        val sexo = intent.extras?.get("sexo")
        when(sexo){
            1 -> textViewResultDescription.setText(R.string.result_description_m)
            0 -> textViewResultDescription.setText(R.string.result_description_f)
        }
        when(status){
            EnumStatus.MAGREZA -> {
                textViewResult1.text = "MAGREZA"
                imageViewResult.setImageResource(R.drawable.result_triste)
            }
            EnumStatus.NORMAL -> {
                textViewResult1.text = "NORMAL"
                imageViewResult.setImageResource(R.drawable.result_feliz)
            }
            EnumStatus.POUCO_ACIMA -> {
                textViewResult1.text = "POUCO ACIMA"
                imageViewResult.setImageResource(R.drawable.result_triste)
            }
            EnumStatus.SOBREPESO -> {
                textViewResult1.text = "SOBREPESO"
                imageViewResult.setImageResource(R.drawable.result_triste)
            }
            EnumStatus.OBESIDADE -> {
                textViewResult1.text = "OBESIDADE"
                imageViewResult.setImageResource(R.drawable.result_triste)
            }
        }
        textViewResult2.text = "Seu IMC é " + String.format("%.1f", float)

        val button = findViewById<Button>(R.id.buttonGoBackForm)
        button.setOnClickListener(::gotoForm)
    }

    fun gotoForm(view: View){
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}