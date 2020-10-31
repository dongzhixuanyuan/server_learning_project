package com.liudong.servlet

import com.google.gson.Gson
import com.liudong.http.ErrorRes
import com.liudong.service.user.LoginServiceImp
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:05 下午
 */

@WebServlet("/login")
class LoginServlet : BaseServlet() {

    private val loginService by lazy {
        LoginServiceImp()
    }


    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val name = req.getParameter("name")
        val password = req.getParameter("password")
        if (name.isNullOrEmpty() || password.isNullOrEmpty()) {
            resp.writer.write(Gson().toJson(ErrorRes(code = 400, msg = "用户名或者密码为空", data = "")))
            return
        }
        val user = loginService.login(name, password)
        resp.writer.write(Gson().toJson(user))
    }


}