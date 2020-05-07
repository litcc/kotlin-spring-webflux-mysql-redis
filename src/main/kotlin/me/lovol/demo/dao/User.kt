package me.lovol.demo.dao

import lombok.AllArgsConstructor
import org.springframework.data.annotation.Id

class User {

    @Id var id: Long? = null
        get() = field
        set(value) {
            field = value
        }
    var username: String? = null
        get() = field
        set(value) {
            field = value
        }

    var password: String? = null
        get() = field
        set(value) {
            field = value
        }

    var email: String? = null
        get() = field
        set(value) {
            field = value
        }
}