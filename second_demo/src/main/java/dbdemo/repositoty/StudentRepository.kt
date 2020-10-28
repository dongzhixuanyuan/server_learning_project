package dbdemo.repositoty

import dbdemo.entity.Student
import dbdemo.util.JdbcUtils
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/27 11:32 上午
 */
class StudentRepository {

    fun addStudent(student: Student) {
        val statement = JdbcUtils.datasource.getConnection().prepareStatement("insert into student(name ,score,id) values(?,?,?)")
        student.run {
            statement.setString(1, name)
            statement.setDouble(2, score)
            statement.setInt(3, student.id)
        }
        statement.executeUpdate()
    }


    fun listAllStudent(): List<Student> {
        val result = JdbcUtils.datasource.getConnection().prepareStatement("select * from student").executeQuery()
        return queryResultToStudentList(result)
    }

    private fun queryResultToStudentList(result: ResultSet): MutableList<Student> {
        val studentList = mutableListOf<Student>()
        var student: Student? = null
        while (result.next()) {
            student = Student(result.getInt("id"), result.getString("name"), result.getDouble("score"))
            studentList.add(student)
        }
        return studentList
    }

    fun queryStudentById(id: Int): List<Student> {
        val prepareStatement = JdbcUtils.datasource.getConnection().prepareStatement("select * from student where id = ?")
        prepareStatement.setInt(1, id)
        val queryResult = prepareStatement.executeQuery()
        return queryResultToStudentList(queryResult)
    }

    fun deleteStudentById(id: Int): Int {
        val prepareStatement = JdbcUtils.datasource.getConnection().prepareStatement("delete from student where id=?")
        prepareStatement.setInt(1, id)
        return prepareStatement.executeUpdate()
    }

}