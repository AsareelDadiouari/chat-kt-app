package com.demo.chatktapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.demo.chatktapp.models.ContactView

class ContactsArrayAdapter(context: Context, resource: Int, contacts: ArrayList<ContactView>) :
    ArrayAdapter<ContactView>(context, resource, contacts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)
    }
}