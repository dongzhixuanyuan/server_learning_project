package com.magina.antiswindle.const

import com.magina.antiswindle.DynamicConfig
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:48 下午
 */
@Component
class Env {

    @Autowired
    var config:DynamicConfig? = null

    var BASE_VIDEO_DIR: String? = null
        get() {
            val dir = config!!.video!!  //
            FileUtils.forceMkdir(File(dir))
            return dir
        }
    var BASE_IMAGE_DIR:String? = null
        get() {
            val dir = config!!.img!! //""
            FileUtils.forceMkdir(File(dir))
            return dir
        }

}


enum class FileType {
    IMAGE,VIDEO
}
