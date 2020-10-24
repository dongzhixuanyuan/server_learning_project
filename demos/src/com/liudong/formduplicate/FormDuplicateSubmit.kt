package com.liudong.formduplicate

import com.liudong.BaseServlet
import sun.misc.BASE64Encoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 表单重复提交的问题。有三种场景可造成表达重复提交：
 * 1、网络延迟的情况下，用户重复点击提交按钮；
 * 2、表单提交后，刷新页面导致的重复提交；
 * 3、表单提交后，用户点击回退按钮后，再重复提交。
 *
 * 解决方案，通过在session塞入一个随机值，并将该随机值塞给前端，前端提交时将随机值回传，服务端验证从前端收到的随机值和保存在session中的值是否一致。
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/30 7:43 下午
 */

@WebServlet("/formtokengenerate")
class TokenServlet : BaseServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val token = TokenProccessor.instance.makeToken()
        req.session.setAttribute(TokenProccessor.KEY_OF_TOKEN, token)
        req.servletContext.getRequestDispatcher("/FormDuplicateSubmit.jsp").forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        doGet(req, resp)
    }
}

@WebServlet("/verifyformsubmit")
class VerifyFormSubmit : BaseServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.characterEncoding="UTF-8"
        if (isFormValid(req)) {
            println("向数据库中插入数据：${req.getParameter("username")}")
            req.session.removeAttribute(TokenProccessor.KEY_OF_TOKEN)
        } else {
            println("无效提交")
        }
    }

    private fun isFormValid(req: HttpServletRequest): Boolean {
        val tokenFromWeb = req.getParameter(TokenProccessor.KEY_OF_TOKEN)
        val serverToken = req.session.getAttribute(TokenProccessor.KEY_OF_TOKEN)
        return Objects.equals(tokenFromWeb, serverToken)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        doGet(req, resp)
    }
}


class TokenProccessor { /*
     *单例设计模式（保证类的对象在内存中只有一个）
     *1、把类的构造函数私有
     *2、自己创建一个类的对象
     *3、对外提供一个公共的方法，返回类的对象
     */
    private constructor()

    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    fun makeToken(): String {  //checkException
        //  7346734837483  834u938493493849384  43434384
        val token =
            (System.currentTimeMillis() + Random().nextInt(999999999)).toString() + ""
        //数据指纹   128位长   16个字节  md5
        return try {
            val md = MessageDigest.getInstance("md5")
            val md5 = md.digest(token.toByteArray())
            //base64编码--任意二进制编码明文字符   adfsdfsdfsf
            val encoder = BASE64Encoder()
            encoder.encode(md5)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    companion object {

        val KEY_OF_TOKEN = "KEY_OF_TOKEN"


        /**
         * 返回类的对象
         * @return
         */
        val instance = TokenProccessor()

    }
}