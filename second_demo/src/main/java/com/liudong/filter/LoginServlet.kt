package com.liudong.filter

import com.liudong.BaseServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/24 9:44 上午
 */
@WebServlet("/login")
class LoginServlet: BaseServlet() {

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        super.doPost(req, resp)
        val name = req.getParameter("name")
        val password = req.getParameter("password")
        if (name.isNullOrEmpty() || password.isNullOrEmpty()) {
            resp.sendRedirect(req.contextPath+"/login.jsp")
            return
        }
        if (name == "admin" && password == "123") {
            req.session.setAttribute("name",name)
            resp.sendRedirect(req.contextPath+"/download.jsp")
        }else {
            resp.sendRedirect(req.contextPath+"/login.jsp")
        }
    }
}