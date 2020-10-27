package com.liudong

import java.net.URLEncoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/30 6:00 下午
 */
@WebServlet("/cookie")
class CookieServlet : BaseServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.characterEncoding = "UTF-8"
        resp.contentType = "text/html;charset=UTF-8"
        req.cookies?.forEach {
            if (it.name == "lastAccessTime") {
                resp.writer.write(Date(it.value.toLong()).toLocaleString())
            }
        } ?: resp.writer.write("first time ！")
        val cookie = Cookie("lastAccessTime", System.currentTimeMillis().toString())
        cookie.maxAge = 24 * 60 * 60 //如果不设置maxAge，那么该cookie的存活时间就是该回话期间，浏览器一关闭那么cookie就丢失了。
        //将cookie的有效期设置为0，命令浏览器删除该cookie
        val userNameCookie = Cookie("username",URLEncoder.encode("孤傲苍狼","UTF-8"))
        resp.addCookie(userNameCookie)
        resp.addCookie(cookie)
    }
}