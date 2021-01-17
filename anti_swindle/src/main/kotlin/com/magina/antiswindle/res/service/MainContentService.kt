package com.magina.antiswindle.res.model.service

import com.magina.antiswindle.const.Env
import com.magina.antiswindle.res.mapper.ResItemMapper
import com.magina.antiswindle.res.model.ItemResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.File

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:51 下午
 */

@Component
class MainContentService {

    @Autowired
    var resItemMapper: ResItemMapper? = null


    fun showAll():List<ItemResource> {
       return resItemMapper!!.allRes()
    }

    fun addRes(res: ItemResource): Int {
        return resItemMapper!!.addRes(res)
    }


    fun deleteRes(id: Int): Int{
        return resItemMapper!!.deleteRes(id)
    }

    fun updateRes(newRes: ItemResource): Int{
        return resItemMapper!!.updateRes(newRes)
    }

    fun queryRes(id: Int): ItemResource? {
        return resItemMapper!!.queryRes(id)
    }


    fun getVideo(videoUrl: String): Resource? {
        val absolutePath = Env.BASE_VIDEO_DIR  + videoUrl
        val videofile = File(absolutePath)
        if (videofile.exists()) {
            return FileSystemResource(absolutePath)
        } else {
            return null
        }
    }

}