package com.liudong.http

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 8:33 下午
 */


open class BaseResponse(val code: Int, val msg: String, val data: String) {
}

class ErrorRes(code: Int, msg: String, data: String) : BaseResponse(code, msg, data) {

}