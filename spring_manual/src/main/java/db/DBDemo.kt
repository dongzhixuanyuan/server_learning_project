package db

import Student
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException
import javax.sql.DataSource


/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/14 2:34 下午
 */


open interface StudentDAO {
    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    fun setDataSource(ds: DataSource?)

    /**
     * This is the method to be used to create
     * a record in the Student table.
     */
    fun create(name: String?, age: Int?)

    /**
     * This is the method to be used to list down
     * a record from the Student table corresponding
     * to a passed student id.
     */
    fun getStudent(id: Int?): db.Student?

    /**
     * This is the method to be used to list down
     * all the records from the Student table.
     */
    fun listStudents(): List<db.Student?>?

    /**
     * This is the method to be used to delete
     * a record from the Student table corresponding
     * to a passed student id.
     */
    fun delete(id: Int?)

    /**
     * This is the method to be used to update
     * a record into the Student table.
     */
    fun update(id: Int?, age: Int?)
}

class Student {
    var age: Int? = null
    var name: String? = null
    var id: Int? = null
}
class StudentMapper : RowMapper<db.Student?> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): db.Student {
        val student = db.Student()
        student.id = rs.getInt("id")
        student.name = rs.getString("name")
        student.age = rs.getInt("age")
        return student
    }
}

class StudentJDBCTemplate : StudentDAO {
    private var dataSource: DataSource? = null
    private var jdbcTemplateObject: JdbcTemplate? = null
    override fun setDataSource(dataSource: DataSource?) {
        this.dataSource = dataSource
        jdbcTemplateObject = JdbcTemplate(dataSource)
    }

    override fun create(name: String?, age: Int?) {
        val SQL = "insert into Student (name, age) values (?, ?)"
        jdbcTemplateObject!!.update(SQL, name, age)
        println("Created Record Name = $name Age = $age")
        return
    }

    override fun getStudent(id: Int?): db.Student? {
        val SQL = "select * from Student where id = ?"
        return jdbcTemplateObject!!.queryForObject(SQL, arrayOf<Any?>(id), StudentMapper())
    }

    override fun listStudents(): List<db.Student?>? {
        val SQL = "select * from Student"
        return jdbcTemplateObject!!.query(
            SQL,
            StudentMapper()
        )
    }

    override fun delete(id: Int?) {
        val SQL = "delete from Student where id = ?"
        jdbcTemplateObject!!.update(SQL, id)
        println("Deleted Record with ID = $id")
        return
    }

    override fun update(id: Int?, age: Int?) {
        val SQL = "update Student set age = ? where id = ?"
        jdbcTemplateObject!!.update(SQL, age, id)
        println("Updated Record with ID = $id")
        return
    }
}