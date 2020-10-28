package com.liudong

import java.io.FileInputStream
import java.net.URLEncoder
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/8/24 12:46 下午
 */
class DownloadServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        kotlin.runCatching {
            val realPath = servletContext.getRealPath("/download/森林公园.jpeg")
            val fileName = realPath.substringAfterLast('/')
            resp.setHeader("content-disposition", "attachment;filename=${URLEncoder.encode(fileName,"UTF-8")}")
            val fileInputStream = FileInputStream(realPath)
            var len = 0
            val buffer = ByteArray(1024)
            do {
                len = fileInputStream.read(buffer)
                resp.outputStream.write(buffer, 0, len)
            } while (len > 0)

            fileInputStream.close()
            resp.status = HttpServletResponse.SC_OK
        }.onFailure {
            resp.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }

    }
}