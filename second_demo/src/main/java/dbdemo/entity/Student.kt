package dbdemo.entity

import java.sql.Date

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/27 11:26 上午
 */
 class Student(val id: Int, val name: String, val score: Double) {
  constructor():this(0,"",0.0)
}