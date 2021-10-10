package com.ahmetkanat.kdv_calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.ahmetkanat.kdv_calculate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener{
            hesapla()
        }

    }
    fun hesapla(){

        val hesapla = binding.hesaplanacakTutarEditText.text.toString()

        val tutar = hesapla.toDoubleOrNull()

        if(tutar == null){
            binding.sonuc.text = ""
            return
        }

        val secilenRadio = binding.radioGroup.checkedRadioButtonId

        val kdvYuzdesi = when (secilenRadio){

            R.id.kdv_0_8 -> 0.008
            R.id.kdv_1_8 -> 0.018
            else -> 0.18
        }

        var kdvliFiyat = (kdvYuzdesi * tutar) + tutar

        val yuvarla = binding.switch1.isChecked

        if(yuvarla){
            kdvliFiyat = kotlin.math.ceil(kdvliFiyat)
        }

        binding.sonuc.text = "Kdv'li Fiyatı : ${kdvliFiyat}"

    }
}