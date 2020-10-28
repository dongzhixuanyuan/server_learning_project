package com.liudong

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/29 8:17 上午
 */
class CharsetServlet:HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        outputChinese(resp)
    }

    private fun outputChinese(resp: HttpServletResponse) {
        val data = "中国"
        resp.setHeader("content-type","text/html;charset=UTF-8")
        resp.outputStream.write(data.toByteArray())
    }


}