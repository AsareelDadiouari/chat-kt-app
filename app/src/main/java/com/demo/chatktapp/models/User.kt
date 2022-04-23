package com.demo.chatktapp.models

data class User(
    var username: String,
    val deviceId: String,
) : Entity()