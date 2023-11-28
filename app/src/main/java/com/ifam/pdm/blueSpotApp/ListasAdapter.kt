package com.ifam.pdm.blueSpotApp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListasAdapter(private val listaDeString: MutableList<String>) :
    RecyclerView.Adapter<ListasAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<TextView>(R.id.item_lista_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listas, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listaDeString.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaDeString[position]
        holder.item.text = item
    }
}