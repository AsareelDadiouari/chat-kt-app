package com.demo.chatktapp.models

import java.util.*

data class Message(
    val id: UUID = UUID.randomUUID(),
    val payload: String,
    val sender: User,
) : Entity()
