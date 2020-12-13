package com.magina.antiswindle.res.model.service

import com.magina.antiswindle.res.mapper.ResItemMapper
import com.magina.antiswindle.res.model.ItemResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:51 下午
 */

@Component
class MainContentService {

    @Autowired
    var resItemMapper:ResItemMapper? = null

    fun addRes(res: ItemResource):Int {
        return resItemMapper!!.addRes(res)
    }

}