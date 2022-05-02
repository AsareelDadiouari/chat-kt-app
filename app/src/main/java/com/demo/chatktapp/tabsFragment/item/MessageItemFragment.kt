package com.demo.chatktapp.tabsFragment.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.chatktapp.R

/**
 * Fragemnt pour l'affichage d'un message dans le menu principal
 */
class MessageItemFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_item, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MessageItemFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}