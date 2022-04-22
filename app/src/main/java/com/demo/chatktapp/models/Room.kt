package com.demo.chatktapp.models

import java.util.*

data class Room(
    val id: UUID = UUID.randomUUID(),
    var messages: MutableList<Message>,
) : Entity()
