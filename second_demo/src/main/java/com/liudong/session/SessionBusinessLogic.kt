package com.liudong.session

import com.liudong.session.DB.all
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

/**
 * @description 通过session来实现一个简单的业务逻辑。该业务逻辑执行时，假定浏览器阻止了所有的cookie，即默认服务器将创建的session的sessionId通过cookie发送给浏览器了。
 * 只能曲线救国，通过[response.encodeURL]以及[response.encodeRedirectUrl]]来实现,这2个方法非常智能，当检测到浏览器允许cookie时，就不会进行重写，
 * 当检测到浏览器不允许cookie时就会重写url，并把JSESSIONID带上。
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/30 6:45 下午
 */


//首页：列出所有书
@WebServlet("/indexservlet")
class IndexServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html;charset=UTF-8"
        val out = response.writer
        //创建Session
        request.session
        out.write("本网站有如下书：<br/>")
        val set = DB.all.entries
        for ((_, book) in set) {
            var url = request.contextPath + "/BuyServlet?id=" + book.id
            //response. encodeURL(java.lang.String url)用于对表单action和超链接的url地址进行重写
            url = response.encodeURL(url) //将超链接的url地址进行重写
            out.println(book.name + "   <a href='" + url + "'>购买</a><br/>")
        }
    }

    @Throws(ServletException::class, IOException::class)
    public override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }
}


/**
 * @author gacl
 * 模拟数据库
 */
internal object DB {
    private val map: MutableMap<String, Book> = LinkedHashMap()
    val all: Map<String, Book>
        get() = map

    init {
        map["1"] = Book("1", "javaweb开发")
        map["2"] = Book("2", "spring开发")
        map["3"] = Book("3", "hibernate开发")
        map["4"] = Book("4", "struts开发")
        map["5"] = Book("5", "ajax开发")
    }
}

internal class Book {
    var id: String? = null
    var name: String? = null

    constructor() : super() {}
    constructor(id: String?, name: String?) : super() {
        this.id = id
        this.name = name
    }

}

@WebServlet("/BuyServlet")
class BuyServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val id = request.getParameter("id")
        val book = all[id] //得到用户想买的书
        val session = request.session
        var list: Any? = session.getAttribute("list") //得到用户用于保存所有书的容器
        if (list == null) {
            list = mutableListOf<Book>()
            session.setAttribute("list", list)
        }
        if (book != null) {
            (list as MutableList<Book>).add(book)
        }
        //response. encodeRedirectURL(java.lang.String url)用于对sendRedirect方法后的url地址进行重写
        val url = response.encodeRedirectURL(request.contextPath + "/ListCartServlet")
        println(url)
        response.sendRedirect(url)
    }

    @Throws(ServletException::class, IOException::class)
    public override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }
}

@WebServlet("/ListCartServlet")
class ListCartServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html;charset=UTF-8"
        val out = response.writer
        val session = request.session
        val list: List<Book> =
            session.getAttribute("list") as List<Book>
        if (list == null || list.size == 0) {
            out.write("对不起，您还没有购买任何商品!!")
            return
        }

        //显示用户买过的商品
        out.write("您买过如下商品:<br>")
        for (book in list) {
            out.write(book.name + "<br/>")
        }
    }

    @Throws(ServletException::class, IOException::class)
    public override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }
}