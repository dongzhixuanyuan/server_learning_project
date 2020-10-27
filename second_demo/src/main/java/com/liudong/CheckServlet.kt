package com.liudong

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/29 11:22 上午
 */
@WebServlet("/formCheck")
class CheckServlet : BaseServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.setHeader("content-type","text/html;charset=UTF-8")
        listOf("validateCode1","validateCode2","validateCode3","validateCode4").forEach {
            if (checkVerifyCode(req,it)) {
                resp.outputStream.write("$it 验证通过\n".toByteArray())
            }else {
                resp.outputStream.write("$it 验证失败\n".toByteArray())
            }
        }
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        doGet(req, resp)
    }


    private fun checkVerifyCode(req: HttpServletRequest, id: String): Boolean {
        if (id.isEmpty()) {
            return false
        }
        val clientVerifycode = req.getParameter(id)
        val serveVerifyCode = req.session.getAttribute(id)
        if (serveVerifyCode != null && serveVerifyCode is String && serveVerifyCode == clientVerifycode) {
            return true
        }
        return false
    }

}