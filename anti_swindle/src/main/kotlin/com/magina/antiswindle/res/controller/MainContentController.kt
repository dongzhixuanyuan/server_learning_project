package com.magina.antiswindle.res.model.controller

import com.magina.antiswindle.common.ResponseBean
import com.magina.antiswindle.const.Env
import com.magina.antiswindle.res.model.ItemResource
import com.magina.antiswindle.res.model.service.MainContentService
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties
import org.springframework.core.io.InputStreamResource
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
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.system.exitProcess

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
    fun allRes(model:Model):String {
        val allItem = resItemService!!.showAll()
        model.addAttribute("itemResults",allItem)
        return "listResItem"
    }


    @ResponseBody
    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addRes(@RequestBody res: ItemResource): ResponseBean<Void> {
        val result = resItemService!!.addRes(res)
        if (result > 0) {
            return ResponseBean.successWithNoData()
        }
        return ResponseBean.fail(500, "server error")
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
        var originalFilename = myfile.originalFilename
        val name = myfile.name
        val size = myfile.size
        var extensionName = ""
        if (!originalFilename.isNullOrEmpty()) {
            extensionName = originalFilename.substring(originalFilename.lastIndexOf("."))
        }
        var newFileName = UUID.randomUUID().toString()
        val outputFile = File(Env.BASE_DIR + newFileName + extensionName)
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        FileCopyUtils.copy(myfile.inputStream, FileOutputStream(outputFile))
        return ResponseBean.successWithNoData()

    }


}