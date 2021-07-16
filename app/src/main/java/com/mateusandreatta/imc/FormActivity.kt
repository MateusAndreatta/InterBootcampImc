package com.mateusandreatta.imc

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form.*
import java.lang.Exception


class FormActivity : AppCompatActivity() {

    var sexoEscolhido: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        buttonGoToResult.setOnClickListener(::gotoResult)
        imageButtonFeminino.setOnClickListener(::setFeminino)
        imageButtonMasculino.setOnClickListener(::setMasculino)
    }

    fun gotoResult(view: View){

        if( editTextPeso.text.isBlank() || editTextAltura.text.isBlank() || sexoEscolhido == null ){
            Toast.makeText(this, getString(R.string.toast_preencha_todos_campos), Toast.LENGTH_SHORT).show()
        }else{
            try {
                val peso   = editTextPeso.text.toString().replace(",",".").toFloat()
                val altura = editTextAltura.text.toString().replace(",",".").toFloat()

                if(peso <= 0f || altura <= 0f){
                    Toast.makeText(this, getString(R.string.toast_valores_negativos_zero), Toast.LENGTH_SHORT).show()
                    return
                }
                val calcIMC = calcIMC(peso, altura)

                val intentResultado = Intent(this, ResultActivity::class.java)

                val result = getStatus(calcIMC)
                result.let { intentResultado.putExtra("status", it) }
                intentResultado.putExtra("imc",calcIMC)
                intentResultado.putExtra("sexo",sexoEscolhido)

                resetView()
                startActivity(intentResultado)
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            }catch (ex: Exception){
                Toast.makeText(this, getString(R.string.toast_confira_valores_informados), Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun getStatus(imc:Float): EnumStatus? {
        // valores de referencia: http://www.lilianspeziali.com.br/tabela-de-imc/
        var resultadoEnum : EnumStatus? = null

        if(sexoEscolhido == 0){
            when{
                imc < 19.1 -> resultadoEnum = EnumStatus.MAGREZA
                imc in 19.1..25.8 -> resultadoEnum = EnumStatus.NORMAL
                imc in 25.9..27.3 -> resultadoEnum = EnumStatus.POUCO_ACIMA
                imc in 27.4..32.3 -> resultadoEnum = EnumStatus.SOBREPESO
                imc > 32.3 -> resultadoEnum = EnumStatus.OBESIDADE
            }
        }
        if(sexoEscolhido == 1){
            when{
                imc < 20.7 -> resultadoEnum = EnumStatus.MAGREZA
                imc in 20.7..26.4 -> resultadoEnum = EnumStatus.NORMAL
                imc in 26.5..27.8 -> resultadoEnum = EnumStatus.POUCO_ACIMA
                imc in 27.9..31.1 -> resultadoEnum = EnumStatus.SOBREPESO
                imc > 31.1 -> resultadoEnum = EnumStatus.OBESIDADE
            }
        }

        return resultadoEnum
    }

    fun calcIMC(peso:Float, altura: Float): Float {
        // IMC = peso/ (altura x altura)
        return peso / (altura * altura)
    }

    fun setFeminino(view: View){
        sexoEscolhido = 0
        textViewFeminino.setTypeface(null, Typeface.BOLD)
        textViewMasculino.setTypeface(null, Typeface.NORMAL)
    }

    fun setMasculino(view: View){
        sexoEscolhido = 1
        textViewFeminino.setTypeface(null, Typeface.NORMAL)
        textViewMasculino.setTypeface(null, Typeface.BOLD)
    }

    fun resetView(){
        sexoEscolhido = null
        editTextPeso.text.clear()
        editTextAltura.text.clear()
        textViewMasculino.setTypeface(null, Typeface.NORMAL)
        textViewFeminino.setTypeface(null, Typeface.NORMAL)
    }
}