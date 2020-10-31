package com.liudong.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 8:42 下午
 */
@WebFilter("*")
class CommonFilter:Filter {
    override fun init(filterConfig: FilterConfig?) {
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        request.characterEncoding = "UTF-8"
        response.characterEncoding = "UTF-8"
        chain.doFilter(request, response)
    }

    override fun destroy() {
    }
}