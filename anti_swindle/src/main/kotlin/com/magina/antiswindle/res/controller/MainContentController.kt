package com.magina.antiswindle.res.model.controller

import com.magina.antiswindle.common.ResonseBean
import com.magina.antiswindle.res.model.ItemResource
import com.magina.antiswindle.res.model.service.MainContentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:50 下午
 */
@RestController
@RequestMapping("/res")
class MainContentController {

    @Autowired
    var resItemService:MainContentService? = null

    @RequestMapping("/add")
    fun addRes(@RequestBody res: ItemResource):ResonseBean<Void> {
        val result = resItemService!!.addRes(res)
        if (result > 0) {
            return ResonseBean.successWithNoData()
        }
        return ResonseBean.
                fail(500,"server error")
    }


}