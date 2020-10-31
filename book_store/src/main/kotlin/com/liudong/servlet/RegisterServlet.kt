package com.liudong.servlet

import com.google.gson.Gson
import com.liudong.http.BaseResponse
import com.liudong.http.ErrorRes
import com.liudong.service.user.LoginServiceImp
import com.liudong.util.CommonUtils
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 9:35 下午
 */
@WebServlet("/register")
class RegisterServlet : BaseServlet() {

    private val loginService by lazy {
        LoginServiceImp()
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val name = req.getParameter("name")
        val password = req.getParameter("password")
        val number = req.getParameter("number")
        if (CommonUtils.parameterVerify(name, password, number)) {
            resp.writer.write(Gson().toJson(ErrorRes(code = 400, msg = "用户名或者密码为空", data = "")))
            return
        }
        val user = loginService.userRegister(name, password, number)
        if (user != null) {
            resp.writer.write(Gson().toJson(BaseResponse(200, "OK", Gson().toJson(user))))
        } else {
            resp.writer.write(Gson().toJson(ErrorRes(code = 500, msg = "注册失败", data = "")))
        }

    }
}