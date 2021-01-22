package com.magina.antiswindle.security

import com.google.gson.Gson
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

/**
 * @description 文件描述
 *
 * @author liudong (735106520@qq.com)
 * @date 2021/1/21 5:22 下午
 */

// 客户端token格式   {"token":"abcd","expires":1234567890}

@Component
class TokenProvider {
    var secretKey: String = "liudong"
    var tokenValidity = 3600

    // 生成token
    fun createToken(userDetails: UserDetails): Token {
        val expires = System.currentTimeMillis() + 1000L * tokenValidity
        val token = computeSignature(userDetails, expires)
        return Token(token, expires)
    }

    // 验证token
    fun validateToken(authToken: String?, userDetails: UserDetails): Boolean {
        val token = Gson().fromJson(authToken, Token::class.java)
            ?: return false
        return if (token.token != userDetails.username) {
            false
        } else token.expires >= Calendar.getInstance().timeInMillis
    }

    fun computeSignature(userDetails: UserDetails, expires: Long): String {
        // 一些特有的信息组装 ,并结合某种加密活摘要算法
        return userDetails.username
    }
}

class Token(var token: String, var expires: Long)