package com.magina.antiswindle.res.mapper

import com.magina.antiswindle.res.model.ItemResource
import org.apache.ibatis.annotations.Mapper


/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:53 下午
 */
@Mapper
interface ResItemMapper {

    fun allRes():List<ItemResource>

    fun addRes(res: ItemResource): Int

    fun deleteRes(id: Int): Int

    fun updateRes(newRes: ItemResource): Int

    fun queryRes(id: Int): ItemResource
}