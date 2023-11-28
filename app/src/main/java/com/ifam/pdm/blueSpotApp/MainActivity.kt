package com.ifam.pdm.blueSpotApp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ifam.pdm.blueSpotApp.databinding.TelaPrincipalAppBinding
import com.ifam.pdm.blueSpotApp.ui.Main
import com.ifam.pdm.blueSpotApp.ui.Propriedade
import com.ifam.pdm.blueSpotApp.ui.home


class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://blue-spot-kotlin-api-production.up.railway.app/"
    lateinit var btnCadastrarLocador: Button
    lateinit var btnhome: Button
    lateinit var btnCadastrarPropriedade: Button

    private lateinit var binding: TelaPrincipalAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaPrincipalAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Main())

        binding.botaoNavegacao.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> replaceFragment(Main())
                R.id.navigation_conta -> replaceFragment(home())
                R.id.navigation_favoritos -> replaceFragment(home())
                R.id.navigation_propriedades -> replaceFragment(Propriedade())
                else -> {

                }

            }
            true
        }

//        btnCadastrarLocador.setOnClickListener {
//            val cadastroIntent = Intent(this, CadastrarLocadorActivity::class.java)
//            startActivity(cadastroIntent)
//        }
//        btnhome.setOnClickListener {
//            val homeIntent = Intent(this, HomeActivity::class.java)
//            startActivity(homeIntent)
//        }
//        btnCadastrarPropriedade.setOnClickListener {
//            val propriedadeIntent = Intent(this, CadastrarPropriedadeActivity::class.java)
//            startActivity(propriedadeIntent)
//        }
    }

   private fun init() {
       btnCadastrarLocador = findViewById(R.id.btn_cadastrar_locador)
     btnhome = findViewById(R.id.btn_home)
      btnCadastrarPropriedade = findViewById(R.id.btn_cadastrar_prop)
  }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}