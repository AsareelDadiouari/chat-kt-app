package com.demo.chatktapp.models

import java.util.*

abstract class Entity {
    var createdAt: Date = Calendar.getInstance().time
    var updatedAt: Date = Calendar.getInstance().time
}