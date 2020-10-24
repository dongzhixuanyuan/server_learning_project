package com.liudong

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 请求转发
 *4.2、请求重定向和请求转发的区别
　　一个web资源收到客户端请求后，通知服务器去[调用]另外一个web资源进行处理，称之为请求转发/307。
　　一个web资源收到客户端请求后，通知浏览器去[访问]另外一个web资源进行处理，称之为请求重定向/302。
    该结论可以通过sendRedirect和forward的目标路径组成区别看出来
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/29 6:03 下午
 */

@WebServlet("/forward")
class ForwardServlet:BaseServlet(){
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        super.doGet(req, resp)
        val data = "大家好，我是孤傲苍狼，我正在总结JavaWeb"
        req.setAttribute("data",data)
//        重定向和请求转发的区别
        req.getRequestDispatcher("/forward.jsp").forward(req,resp)



    }
}