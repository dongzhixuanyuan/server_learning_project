package com.liudong.repository.book

import com.liudong.entity.Book
import com.liudong.entity.BookCase

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 10:20 下午
 */
interface IBookRepo {
    fun insertBook(book: Book)

    fun insertBookCase(bookCase: BookCase):Boolean
}