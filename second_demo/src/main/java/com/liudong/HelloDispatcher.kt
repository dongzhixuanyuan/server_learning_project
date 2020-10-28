package com.liudong

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 资源转发的代码，没有实现。暂时就不折腾了
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/23 8:14 下午
 */
class HelloDispatcher : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.writer.println("fffff")
        val data = "<h1><font color='red'>abcdefghjkl</font></h1>";
        resp.outputStream.write(data.toByteArray())
        val requestDispatcher = servletContext.getRequestDispatcher("/servlet/hello2")
        requestDispatcher.forward(req, resp)
    }
}