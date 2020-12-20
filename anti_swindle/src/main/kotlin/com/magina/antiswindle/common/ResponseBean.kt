package com.magina.antiswindle.common

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 6:21 下午
 */
 class ResponseBean<T>(var code: Int, val msg: String, val data: T?) {
     companion object {
         const val SUCCESS = "success"
         fun successWithNoData():ResponseBean<Void> {
             return ResponseBean(200, SUCCESS,null)
         }
         fun fail(errorCode:Int,errorMsg:String):ResponseBean<Void>{
             return ResponseBean(errorCode,errorMsg,null)
         }
     }
 }