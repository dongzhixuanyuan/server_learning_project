package com.liudong

import java.io.File
import java.io.FileInputStream
import javax.imageio.stream.FileImageInputStream
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/25 10:49 上午
 */
@WebServlet("/filedownload")
class FileDownloadServlet:BaseServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val parameter = req.getParameter("type")
        resp.contentType = "application/x-msdownload"
        when (parameter) {
            "img" -> {
                resp.setHeader("Content-Disposition","attachment;filename="+"test.png")
                val fileInputStream = FileInputStream(javaClass.classLoader.getResource("img/test.png").path)
                val buffer = ByteArray(8 * 1024)
                while (fileInputStream.read(buffer) != -1) {
                    resp.outputStream.write(buffer)
                }
                fileInputStream.close()
            }
            "txt" -> {
                resp.setHeader("Content-Disposition","attachment;filename="+"test.txt")
                val fileInputStream = FileInputStream(javaClass.classLoader.getResource("test.txt").path)
                val buffer = ByteArray(8 * 1024)
                while (fileInputStream.read(buffer) != -1) {
                    resp.outputStream.write(buffer)
                }
                fileInputStream.close()
            }
        }
    }
}