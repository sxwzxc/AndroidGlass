package com.sxwzxc.androidglass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Contact(val name: String, val role: String, val status: String)

class ContactAdapter(private val items: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: TextView = view.findViewById(R.id.tvAvatar)
        val name: TextView = view.findViewById(R.id.tvName)
        val role: TextView = view.findViewById(R.id.tvRole)
        val status: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = items[position]
        holder.avatar.text = contact.name.first().toString()
        holder.name.text = contact.name
        holder.role.text = contact.role
        holder.status.text = contact.status
    }

    override fun getItemCount(): Int = items.size
}
