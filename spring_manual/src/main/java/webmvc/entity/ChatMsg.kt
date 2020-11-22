package webmvc.entity

import com.google.gson.Gson

class ChatMsg {
    var timestamp: Long = 0
    var name: String? = null
    var text: String? = null

    override fun toString(): String {
        return Gson().toJson(this)
    }
}