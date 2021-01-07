package com.magina.antiswindle.backgroundmanager

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*


/**
 * @description thymeleaf测试
 *
 * @author magina (735106520@qq.com)
 * @date 2021/1/5 4:22 下午
 */

@Controller
@RequestMapping("/manager/res")
class ResEditControlelr {

    @RequestMapping("/all")
    fun test(model: Model): String {
        val student = Student("张三", 1)
        val students: MutableList<Student> = ArrayList()
        students.add(student)
        students.add(Student("李四", 2))
        students.add(Student("王五", 3))
        students.add(Student("二麻子", 4))
        students.add(Student("三棒子", 5))

        model.addAttribute("students", students)
        model.addAttribute("selectedstudent",student)
        model.addAttribute("now",Calendar.getInstance().time)
        return "hello"
    }
}

data class Student(val name: String, val id: Int)