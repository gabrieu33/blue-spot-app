package com.ifam.pdm.blueSpotApp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ifam.pdm.blueSpotApp.CadastrarLocadorActivity
import com.ifam.pdm.blueSpotApp.CadastrarPropriedadeActivity
import com.ifam.pdm.blueSpotApp.HomeActivity
import com.ifam.pdm.blueSpotApp.R

private val BASE_URL = "https://blue-spot-kotlin-api-production.up.railway.app/"
private lateinit var btnCadastrarLocador: Button
private lateinit var btnhome: Button
private lateinit var  btnCadastrarPropriedade : Button
@Suppress("UNREACHABLE_CODE")
class Main : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.activity_main, container, false)

        val btnCadastrarLocador = view.findViewById<Button>(R.id.btn_cadastrar_locador)
        val btnhome = view.findViewById<Button>(R.id.btn_home)
        val btnCadastrarPropriedade = view.findViewById<Button>(R.id.btn_cadastrar_prop)

        btnCadastrarLocador.setOnClickListener {
            val cadastroIntent = Intent(activity, CadastrarLocadorActivity::class.java)
            startActivity(cadastroIntent)
        }
        btnhome.setOnClickListener {
            val homeIntent = Intent(activity, HomeActivity::class.java)
            val anyString = "Olha eu aqui!"
            homeIntent.putExtra("id", anyString)
            startActivity(homeIntent)
        }
        btnCadastrarPropriedade.setOnClickListener {
          val propriedadeIntent = Intent(activity, CadastrarPropriedadeActivity::class.java)
          startActivity(propriedadeIntent)
       }
        return view
    }


    companion object {

    }
}