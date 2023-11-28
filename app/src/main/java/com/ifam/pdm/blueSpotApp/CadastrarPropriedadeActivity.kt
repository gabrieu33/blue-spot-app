package com.ifam.pdm.blueSpotApp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifam.pdm.blueSpotApp.core.dtos.PropertyCreationDto
import com.ifam.pdm.blueSpotApp.core.dtos.UserCreationDto
import com.ifam.pdm.blueSpotApp.core.entities.Landlord
import com.ifam.pdm.blueSpotApp.core.entities.Property
import com.ifam.pdm.blueSpotApp.core.enums.CivilState
import com.ifam.pdm.blueSpotApp.core.enums.PropertyType
import com.ifam.pdm.blueSpotApp.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class CadastrarPropriedadeActivity : AppCompatActivity() {
    private val BASE_URL = "https://blue-spot-kotlin-api-production.up.railway.app/"
    lateinit var enderecoET: EditText
    lateinit var descricaoET: EditText
    lateinit var valorET: EditText
    lateinit var tipoSpinner: Spinner
    lateinit var garagemSwitch: Switch
    lateinit var restricoesEditText: EditText
    lateinit var mobiliasEditText: EditText
    lateinit var restricoesRecyclerView: RecyclerView
    lateinit var mobiliasRecyclerView: RecyclerView
    lateinit var restricoesImageButton: ImageButton
    lateinit var mobiliasImageButton: ImageButton
    lateinit var btnCadastrarPropriedade: Button
    private val restricoes = mutableListOf<String>()
    private val mobilias = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_propriedade)

        init()
        restricoesRecyclerView.layoutManager = LinearLayoutManager(this@CadastrarPropriedadeActivity)
        mobiliasRecyclerView.layoutManager = LinearLayoutManager(this@CadastrarPropriedadeActivity)

        val adapterRestricoes = ListasAdapter(restricoes)
        val adapterMobilias = ListasAdapter(mobilias)

        restricoesRecyclerView.adapter = adapterRestricoes
        mobiliasRecyclerView.adapter = adapterMobilias

        tipoSpinner.adapter = ArrayAdapter<PropertyType>(
            this@CadastrarPropriedadeActivity,
            android.R.layout.simple_spinner_item,
            PropertyType.values()
        )
        btnAddAction(restricoesImageButton, restricoesEditText, restricoes, restricoesRecyclerView)
        btnAddAction(mobiliasImageButton, mobiliasEditText, mobilias, mobiliasRecyclerView)

        btnCadastrarPropriedade.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                cadastrarPropriedade()
            }
        }
    }

    private fun init() {
        enderecoET = findViewById(R.id.endereco_propriedade_et)
        descricaoET = findViewById(R.id.descricao_input_et)
        valorET = findViewById(R.id.valor_input_et)
        tipoSpinner = findViewById(R.id.tipo_spinner)
        garagemSwitch = findViewById(R.id.garagem_switch)
        restricoesEditText = findViewById(R.id.restricoes_et)
        mobiliasEditText = findViewById(R.id.mobilias_et)
        restricoesRecyclerView = findViewById(R.id.restricoes_rv)
        mobiliasRecyclerView = findViewById(R.id.mobilias_rv)
        restricoesImageButton = findViewById(R.id.btn_add_restricao)
        mobiliasImageButton = findViewById(R.id.btn_add_mobilia)
        btnCadastrarPropriedade = findViewById(R.id.btn_cadastrar_propriedade)

    }

    private fun btnAddAction(
        btn: ImageButton,
        editText: EditText,
        lista: MutableList<String>,
        rv: RecyclerView
    ) {
        btn.setOnClickListener {
            lista.add(editText.text.toString())
            editText.text.clear()
            editText.requestFocus()
            rv.adapter?.notifyDataSetChanged()
        }
    }

    private fun cadastrarPropriedade() {
        val retrofitClient = NetworkUtils.getRetrofitInstance(BASE_URL)
        val endpoint = retrofitClient.create(ApiRequests::class.java)

        val newProperty = PropertyCreationDto(
            enderecoET.text.toString(),
            descricaoET.text.toString(),
            valorET.text.toString().toDouble(),
            PropertyType.valueOf(tipoSpinner.selectedItem.toString()),
            restrictions = restricoes,
            furnishings = mobilias,
            garagemSwitch.isChecked
        )

        endpoint.cadastrarPropriedade(newProperty).enqueue(object : retrofit2.Callback<Property> {
            override fun onResponse(call: Call<Property>, response: Response<Property>) {
                val message = "Propriedade Cadastrada com sucesso!"
                val toast =
                    Toast.makeText(this@CadastrarPropriedadeActivity, message, Toast.LENGTH_SHORT)
                toast.show()
                redirecionaHome()
            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                val toast =
                    Toast.makeText(this@CadastrarPropriedadeActivity, t.message, Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }

    private fun redirecionaHome() {
        val homePropriedades = Intent(this@CadastrarPropriedadeActivity, HomeActivity::class.java)
        startActivity(homePropriedades)
        finish()
    }
}