package com.liudong.servlet

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:04 下午
 */
open class BaseServlet:HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    }
}