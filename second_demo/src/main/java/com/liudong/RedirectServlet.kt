package com.liudong

import java.io.File
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 重定向
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/29 11:00 上午
 */
@WebServlet("/redict")
class RedirectServlet:HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
//        resp.sendRedirect("/servletLearning/index.html")//这个url路径代表的是告知浏览器的地址的，/代表浏览器的根目录，即为webapps目录，"servletLearning"代表是这个服务端应用本身
//        上面的这种"servletLearning"是把项目名字给定死了，是不灵活的，应修改为下面的写法
        resp.sendRedirect(req.contextPath+File.separator+"index.html")
    }
}