package com.liudong

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/24 12:35 下午
 */
class ExpireServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.setDateHeader("expires", System.currentTimeMillis() + 24 * 3600 * 1000)
        resp.outputStream.write("ddddddddd".toByteArray())
    }
}