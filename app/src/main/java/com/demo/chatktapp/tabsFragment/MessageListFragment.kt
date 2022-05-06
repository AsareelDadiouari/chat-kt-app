package com.demo.chatktapp.tabsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.demo.chatktapp.R
import com.demo.chatktapp.adapters.MessagesArrayAdapter
import com.demo.chatktapp.models.MessageView
import java.util.*
import kotlin.collections.ArrayList

/**
 * Fragement contenant la liste des messages
 */
class MessageListFragment : Fragment() {
    private lateinit var messageList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message_list, container, false)


        val arr = ArrayList<MessageView>()
        arr.add(MessageView("", "Asa",
            "the main body of a book or other piece of writing, as distinct" +
                    " from other material such as notes, appendices," +
                    " and illustrations.", Calendar.getInstance().time))

        messageList = view.findViewById(R.id.message_list_view)
        messageList.adapter = MessagesArrayAdapter(requireContext(), R.layout.fragment_message_item, arr)

        return view
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MessageListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}