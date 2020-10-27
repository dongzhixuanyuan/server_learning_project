package com.liudong.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/23 10:20 下午
 */
@WebFilter("/download")
class MyFilter : Filter {
    override fun destroy() {
    }

    override fun doFilter(p0: ServletRequest, p1: ServletResponse, p2: FilterChain) {
        p0.characterEncoding = "UTF-8"
        val sessionName = (p0 as HttpServletRequest).session.getAttribute("name")
        if (sessionName != null) {
            print(sessionName as String)
            p2.doFilter(p0, p1)
        }else {
            (p1 as HttpServletResponse).sendRedirect(p0.contextPath+"/login.jsp")
        }
    }

    override fun init(p0: FilterConfig?) {
    }
}