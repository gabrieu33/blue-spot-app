package com.ifam.pdm.blueSpotApp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ifam.pdm.blueSpotApp.core.dtos.UserCreationDto
import com.ifam.pdm.blueSpotApp.core.entities.Landlord
import com.ifam.pdm.blueSpotApp.core.enums.CivilState
import com.ifam.pdm.blueSpotApp.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class CadastrarLocadorActivity : AppCompatActivity() {
    private val BASE_URL = "https://blue-spot-kotlin-api-production.up.railway.app/"
    lateinit var nomeEt: EditText
    lateinit var emailEt: EditText
    lateinit var senhaEt: EditText
    lateinit var celularEt: EditText
    lateinit var rgEt: EditText
    lateinit var cpfEt: EditText
    lateinit var nacionalidadeEt: EditText
    lateinit var profissaoEt: EditText
    lateinit var enderecoEt: EditText
    lateinit var estadoCivilSpinner: Spinner
    lateinit var btnCadastrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        btnCadastrar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                cadastrarLocador()
            }
        }
    }

    private fun init() {
        nomeEt = findViewById(R.id.nome_et)
        emailEt = findViewById(R.id.email_et)
        senhaEt = findViewById(R.id.senha_et)
        celularEt = findViewById(R.id.celular_et)
        rgEt = findViewById(R.id.rg_et)
        cpfEt = findViewById(R.id.cpf_et)
        nacionalidadeEt = findViewById(R.id.nacionalidade_et)
        profissaoEt = findViewById(R.id.profissao_et)
        enderecoEt = findViewById(R.id.endereco_et)
        estadoCivilSpinner = findViewById(R.id.estado_civil_spinner)
        estadoCivilSpinner.adapter = ArrayAdapter<CivilState>(
            this,
            android.R.layout.simple_spinner_item,
            CivilState.values()
        )
        btnCadastrar = findViewById(R.id.btn_cadastrar)
    }

    private suspend fun cadastrarLocador() {
        val retrofitClient = NetworkUtils.getRetrofitInstance(BASE_URL)
        val endpoint = retrofitClient.create(ApiRequests::class.java)

        val newUser = UserCreationDto(
            nomeEt.text.toString(),
            emailEt.text.toString(),
            senhaEt.text.toString(),
            celularEt.text.toString(),
            nacionalidadeEt.text.toString(),
            CivilState.valueOf(estadoCivilSpinner.selectedItem.toString()),
            profissaoEt.text.toString(),
            rgEt.text.toString(),
            cpfEt.text.toString(),
            enderecoEt.text.toString(),
        )

        endpoint.cadastrarLocador(newUser).enqueue(object : retrofit2.Callback<Landlord> {
            override fun onResponse(call: Call<Landlord>, response: Response<Landlord>) {
                val message = "${response.body()?.name}, você se cadastrou com sucesso!"
                val toast =
                    Toast.makeText(this@CadastrarLocadorActivity, message, Toast.LENGTH_SHORT)
                toast.show()
                redirecionaHome()
            }

            override fun onFailure(call: Call<Landlord>, t: Throwable) {
                val toast =
                    Toast.makeText(this@CadastrarLocadorActivity, t.message, Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }

    private fun redirecionaHome() {
        val homePropriedades = Intent(this@CadastrarLocadorActivity, HomeActivity::class.java)
        startActivity(homePropriedades)
        finish()
    }
}