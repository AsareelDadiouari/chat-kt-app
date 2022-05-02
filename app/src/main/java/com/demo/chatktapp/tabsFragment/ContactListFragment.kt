package com.demo.chatktapp.tabsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.demo.chatktapp.R

/**
 * Fragement contenant la liste des contacts
 */
class ContactListFragment : Fragment() {
    private lateinit var contactList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)

        contactList = view.findViewById(R.id.contact_list_view)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ContactListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}