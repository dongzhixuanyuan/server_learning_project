package com.liudong

import java.io.PrintWriter
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_OK

class Hello2 : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val attribute = servletContext.getAttribute(Hello.KEY_DATA)
        resp?.setContentType("text/html")
        resp?.characterEncoding = "UTF-8"
        val out: PrintWriter = resp?.getWriter()!!
        resp.status = SC_OK
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">")
        out.println("<HTML>")
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>")
        out.println("  <BODY>")
        out.print("    This is ")
//        out.print(Hello2::class.simpleName)
        out.println(", using the GET method")
        attribute?.run {
            out.println(attribute as String)
        }
        out.println("  </BODY>")
        out.println("</HTML>")
        out.flush()
        out.close()
    }
}