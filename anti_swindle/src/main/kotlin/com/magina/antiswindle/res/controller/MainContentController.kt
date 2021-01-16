package com.magina.antiswindle.res.model.controller

import com.magina.antiswindle.common.ResponseBean
import com.magina.antiswindle.const.Env
import com.magina.antiswindle.res.model.Data
import com.magina.antiswindle.res.model.ItemResource
import com.magina.antiswindle.res.model.service.MainContentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.servlet.http.HttpServletRequest


/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:50 下午
 */
@Controller
@RequestMapping("/res")
class MainContentController {


    @Autowired
    var resItemService: MainContentService? = null

    @RequestMapping("/all")
    fun allRes(model: Model): String {
        val allItem = resItemService!!.showAll()
        model.addAttribute("itemResults", allItem)
        return "listResItem"
    }

    @RequestMapping("/page/add")
    fun addItemPage(): String {
        return "page_item_add"
    }


    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addRes(request: HttpServletRequest): String {
        if (request is MultipartHttpServletRequest) {
            val multipartRequest = request
            // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
            val file = multipartRequest.getFile("file")

            val imageName = file?.run {
                saveImageFile(this)
            }
            // 接收其他表单参数
            val name = multipartRequest.getParameter("name")
            val source = multipartRequest.getParameter("source")
            val description = multipartRequest.getParameter("description")
            val data =
                Data(Calendar.getInstance().time.toLocaleString(), source, name, description, "", null, imageName)
            val result = resItemService!!.addRes(ItemResource(null, source, data))
            if (result > 0) {
                return "redirect:/res/all"
            } else {
                return "error"
            }
        } else {
            return "error"
        }
    }

    @RequestMapping("/delete")
    fun deleteRes(id: Int): String {
        try {
            resItemService!!.deleteRes(id)
        } catch (e: Exception) {

        } finally {
            return "redirect:/res/all"
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    fun updateRes(@RequestBody newRes: ItemResource): ResponseBean<Void> {
        try {
            val result = resItemService!!.updateRes(newRes)
            return ResponseBean.successWithNoData()
        } catch (e: Exception) {
            return ResponseBean.fail(500, "更新失败:${e.message}")
        }

    }

    @RequestMapping("/item")
    fun showSingleItem(id: Int): String {
        val result = resItemService!!.queryRes(id)
        if (result == null) {
            return "error_not_found"
        } else {
            return "page_item_add"
        }
    }

    @ResponseBody
    @RequestMapping("/query")
    fun queryRes(id: Int): ResponseBean<ItemResource?> {
        try {
            val result = resItemService!!.queryRes(id)
            return ResponseBean(200, "success", result)
        } catch (e: Exception) {
            return ResponseBean(500, "query fail:${e.message}", null)
        }
    }

    @ResponseBody
    @RequestMapping("/video/download")
    fun downloadVideo(@RequestParam("video") videoUrl: String): ResponseEntity<Resource>? {
        val videoFile = resItemService!!.getVideo(videoUrl)

        val headers = HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s", videoUrl));
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        if (videoFile != null) {
            return ResponseEntity.ok().headers(headers).contentLength(videoFile.contentLength())
                .contentType(MediaType.parseMediaType("video/mp4")).body(videoFile)
        }
        return null
    }

    @ResponseBody
    @RequestMapping("/video/upload")
    fun uploadVideo(@RequestParam("myfile") myfile: MultipartFile): ResponseBean<Void> {
        saveImageFile(myfile)
        return ResponseBean.successWithNoData()
    }

    fun saveImageFile(file: MultipartFile): String {
        var originalFilename = file.originalFilename
        var extensionName = ""
        if (!originalFilename.isNullOrEmpty()) {
            extensionName = originalFilename.substring(originalFilename.lastIndexOf("."))
        }
        var newFileName = UUID.randomUUID().toString()
        val outputFile = File(Env.BASE_DIR + newFileName + extensionName)
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        FileCopyUtils.copy(file.inputStream, FileOutputStream(outputFile))
        return newFileName + extensionName
    }

}
