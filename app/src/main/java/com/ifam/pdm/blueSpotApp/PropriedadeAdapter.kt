package com.ifam.pdm.blueSpotApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ifam.pdm.blueSpotApp.core.entities.Property

class PropriedadeAdapter(private val properties: MutableList<Property>): RecyclerView.Adapter<PropriedadeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val valorTextView = itemView.findViewById<TextView>(R.id.valor_et)
        val tipoTextView = itemView.findViewById<TextView>(R.id.tipo_et)
        val descricaoTextView = itemView.findViewById<TextView>(R.id.descricao_et)
        val propriedadeImage = itemView.findViewById<ImageView>(R.id.propriedade_image)
        val verificadoImage = itemView.findViewById<ImageView>(R.id.verificado_image)
        val garagemImage = itemView.findViewById<ImageView>(R.id.garagem_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_propriedade, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = properties.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = properties[position]
        val valor = "Valor: R$" + item.price.toString()
        holder.valorTextView.text = valor
        holder.tipoTextView.text = item.propertyType.value.uppercase()
        holder.descricaoTextView.text = item.description
        holder.garagemImage.visibility = if (item.hasGarage) View.VISIBLE else View.INVISIBLE
        holder.verificadoImage.visibility = if (item.isVerified) View.VISIBLE else View.INVISIBLE
    }
}