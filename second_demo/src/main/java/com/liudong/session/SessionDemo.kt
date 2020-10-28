package com.liudong.session

import com.liudong.BaseServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/30 6:35 下午
 */
@WebServlet("/session")
class SessionDemo : BaseServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.characterEncoding = "UTF-8"
        resp.contentType = "text/html;charset=UTF-8"
        val httpSession = req.session
        httpSession.setAttribute("data", "孤傲苍狼")
        val sessionId = httpSession.id
        if (httpSession.isNew) {
            resp.writer.write("session创建成功，session的id是：$sessionId")
        } else {
            resp.getWriter().print("服务器已经存在该session了，session的id是：$sessionId")
        }
    }
}