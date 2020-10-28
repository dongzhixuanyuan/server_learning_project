package com.liudong.filter

import com.liudong.BaseServlet
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStream
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件上传servlet
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/24 2:10 下午
 */
@WebServlet("/fileupload")
class FileUploadServlet:BaseServlet() {

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        if (ServletFileUpload.isMultipartContent(req)) {
            val diskFileItemFactory = DiskFileItemFactory()
            val repository = servletConfig.servletContext.getAttribute("javax.servlet.context.tempdir") as File
            diskFileItemFactory.repository = repository
            val servletFileUpload = ServletFileUpload(diskFileItemFactory)
            servletFileUpload.parseRequest(req).forEach {
                if (it.isFormField) {
                    print("表单提交的数据：${it.fieldName}+${it.string}+${it.contentType}")
                } else {
                    val dest = File("${servletContext.getRealPath("file/img")}")
                    it.write(dest)
                }
            }
        }
//
//
//        val bufferReader = BufferedInputStream(req.inputStream,8 * 1024)
////        val dest = "${req.contextPath}${File.separator}${req.getParameter("img")}"
//        val dest = File("${servletContext.getRealPath("file/img")}")
//        if (dest.exists()) {
//            dest.delete()
//        }
//        dest.createNewFile()
//        DiskFileItemFactory
//        val bufferWriter = BufferedOutputStream(FileOutputStream(dest))
//        val buffer = ByteArray(1024)
//        while (bufferReader.read(buffer) != -1) {
//            bufferWriter.write(buffer)
//        }
//        bufferReader.close()
//        bufferWriter.close()

    }
}