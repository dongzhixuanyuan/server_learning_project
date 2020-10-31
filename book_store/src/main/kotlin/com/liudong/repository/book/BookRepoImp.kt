package com.liudong.repository.book

import com.liudong.entity.Book
import com.liudong.entity.BookCase
import com.liudong.util.DbManager
import org.apache.commons.dbutils.DbUtils
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.ArrayListHandler
import org.apache.commons.dbutils.handlers.BeanHandler

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 10:21 下午
 */
class BookRepoImp : IBookRepo {
    override fun insertBook(book: Book) {

    }

    override fun insertBookCase(bookCase: BookCase): Boolean {

        val connection = DbManager.getConnection()
        try {
            val queryRunner = QueryRunner()
            val book = queryRunner.insert(
                connection,
                "insert into book_case (category_name) value (?)",
                BeanHandler(Book::class.java),
                bookCase.category_name)

            return book != null
        } catch (e: Exception) {
            return false
        }
    }
}