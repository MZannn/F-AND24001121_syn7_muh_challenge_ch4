package com.example.chapter4.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter4.R
import com.example.chapter4.model.Notes

class NotesAdapter(
    private val list: List<Notes>,
    private val onEditClick: (Notes) -> Unit,
    private val onDeleteClick: (Notes) -> Unit
) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnEdit: ImageButton = itemView.findViewById<ImageButton>(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById<ImageButton>(R.id.btnDelete)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val content: TextView = itemView.findViewById(R.id.tvContent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.content.text = item.content
        holder.btnDelete.setOnClickListener {
            onDeleteClick(item)
        }
        holder.btnEdit.setOnClickListener {
            onEditClick(item)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}