package com.magina.antiswindle.const

import org.apache.tomcat.util.http.fileupload.FileUtils
import java.io.File

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:48 下午
 */
object Env {

    val BASE_VIDEO_DIR: String
        get() {
            val dir = "/Users/liudong/反诈资料/video/"
            FileUtils.forceMkdir(File(dir))
            return dir
        }
    val BASE_IMAGE_DIR:String
        get() {
            val dir = "/Users/liudong/反诈资料/image/"
            FileUtils.forceMkdir(File(dir))
            return dir
        }

}


enum class FileType {
    IMAGE,VIDEO
}
