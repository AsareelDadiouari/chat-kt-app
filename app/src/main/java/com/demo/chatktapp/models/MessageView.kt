package com.demo.chatktapp.models

import java.util.*

data class MessageView(
    var profilePicture: String,
    var senderName: String,
    var lastMessage: String,
    val messageDate: Date = Calendar.getInstance().time
)
