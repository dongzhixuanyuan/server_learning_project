package com.liudong.entity

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:09 下午
 */
class Book {
    var book_id: Int? = null
    var name: String? = null
    var case: BookCase? = null
}

class BookCase {
    var case_id: Int? = null
    var category_name: String? = null
}