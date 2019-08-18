package com.dznow.project.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dznow.project.R
import com.dznow.project.presentation.model.Contact

class ContactAdapter (contactList: List<Contact>,
                      private val context : Context,
                      private val rowRessource: Int,
                      private val listener: ContactAdapterListner):
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var mContacts: List<Contact> = contactList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val optionView = inflater.inflate(rowRessource, p0, false)
        return ViewHolder(optionView)
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val contact = mContacts[p1]
        p0.name.text = contact.name
        p0.value.text = contact.value

        p0.itemView.setOnClickListener {
            listener.onContactClicked(mContacts[p1])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name : TextView = itemView.findViewById(R.id.name_textView)
        var value : TextView = itemView.findViewById(R.id.value_textView)
    }

    interface ContactAdapterListner {
        fun onContactClicked(contact: Contact)
    }
}