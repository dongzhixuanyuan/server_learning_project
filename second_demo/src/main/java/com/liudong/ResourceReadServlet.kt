package com.liudong

import java.io.InputStream
import java.util.*
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 读取文件资源的代码
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/23 8:43 下午
 */
class ResourceReadServlet:HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType="text/html;charset=UTF-8"
        readSrcDirPropCfgFile(resp)
        resp.writer.println("<hr/>")
        readWebrootDirFile(resp)
        resp.writer.println("<hr/>")
        readPropCfgFile(resp)
    }

    /**
     * 读取包名下的文件的话，"/WEB-INF/classes"+包名全路径
     */
    private fun readPropCfgFile(resp: HttpServletResponse) {
        val inputStream = servletContext.getResourceAsStream("/WEB-INF/classes/db/config/db3.properties")
        parseData(inputStream,resp,"读取src目录下db.config包中的db3.properties")
    }

    /**
     * web根目录的话，路径直接就是"/file"
     */
    private fun readWebrootDirFile(resp: HttpServletResponse) {
        val resourceAsStream = servletContext.getResourceAsStream("/db2.properties")
        parseData(resourceAsStream,resp,"读取web根目录下的db1.properties配置文件：")
    }

    /**
     * 读取src目录下，也就是"/WEB-INF/classes/+类全名
     */
    private fun readSrcDirPropCfgFile(resp: HttpServletResponse) {
        val inputStream = servletContext.getResourceAsStream("/WEB-INF/classes/db1.properties")
        parseData(inputStream, resp,"读取src目录下的db1.properties配置文件：")
    }

    private fun parseData(inputStream: InputStream, resp: HttpServletResponse,type:String) {
        val property = Properties()
        property.load(inputStream)
        val driver = property.getProperty("driver")
        val url = property.getProperty("url")
        val userName = property.getProperty("username")
        val password = property.getProperty("password")
        resp.writer.run {
            println(type)
            println("driver:$driver,url:$url,username:$userName,password:$password")
        }
    }

}