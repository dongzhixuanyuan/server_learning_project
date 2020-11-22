package com.liudong

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/11/22 10:18 上午
 */


@Configuration
@ComponentScan
@EnableTransactionManagement
open class AppConfig {

}