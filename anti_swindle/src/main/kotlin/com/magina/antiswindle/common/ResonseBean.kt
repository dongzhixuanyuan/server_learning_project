package com.magina.antiswindle.common

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 6:21 下午
 */
 class ResonseBean<T>(var code: Int, val msg: String, val data: T?) {
     companion object {
         const val SUCCESS = "success"
         fun successWithNoData():ResonseBean<Void> {
             return ResonseBean(200, SUCCESS,null)
         }
         fun fail(errorCode:Int,errorMsg:String):ResonseBean<Void>{
             return ResonseBean(errorCode,errorMsg,null)
         }
     }
 }