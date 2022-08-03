package com.demo.chatktapp.adapters

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.demo.chatktapp.R
import com.demo.chatktapp.models.MessageView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessagesArrayAdapter(context: Context, resource: Int, messages: ArrayList<MessageView>) :
    ArrayAdapter<MessageView>(context, resource, messages) {
    private val nightModeFlags = context.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertViewTemp = convertView

        if (convertViewTemp == null) {
            convertViewTemp =
                LayoutInflater.from(context).inflate(R.layout.fragment_message_item, parent, false)
        }

        bindData(convertViewTemp, position)
        return convertViewTemp!!
    }

    private fun bindData(view: View?, position: Int) {
        val message = getItem(position)

        val profilePic = view?.findViewById<ImageView>(R.id.profile_picture)
        val contactName = view?.findViewById<TextView>(R.id.contact_name)
        val lastMessage = view?.findViewById<TextView>(R.id.last_message)
        val date = view?.findViewById<TextView>(R.id.last_message_date)

        if (message != null) {
            if (message.profilePicture.isEmpty()){
                when (nightModeFlags) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        profilePic?.setImageResource(R.drawable.ic_profilepic_icon_dark)
                    }
                    Configuration.UI_MODE_NIGHT_NO -> {
                        profilePic?.setImageResource(R.drawable.ic_profilepic_icon_light)
                    }
                }
            } else{
                profilePic?.setImageBitmap(decodeBase64Image(message.profilePicture))
            }

            contactName?.text = message.senderName
            lastMessage?.text = message.lastMessage
            date?.text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ROOT).format(message.messageDate).toString()
        }
    }

    private fun decodeBase64Image(encodedString: String): Bitmap {
        val decodedString = Base64.decode(encodedString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }
}