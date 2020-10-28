package dbdemo

import com.google.gson.Gson
import com.liudong.BaseServlet
import dbdemo.entity.Student
import dbdemo.repositoty.StudentRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/27 2:01 下午
 */
@WebServlet("/student")
class StudentServlet : BaseServlet() {
    val studentRepository = StudentRepository()

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.characterEncoding = "UTF-8"
        val operationType = req.getParameter("type")
        when (operationType) {
            "queryall" -> {
                val listAllStudent = studentRepository.listAllStudent()
                val jsonResult = Gson().toJson(listAllStudent)
                resp.writer.print(jsonResult)
            }
            "querybyid" -> {
                val parameterId = req.getParameter("id")
                if (parameterId != null) {
                    val id = parameterId.toInt()
                    val queryStudentById = studentRepository.queryStudentById(id)
                    resp.writer.print(Gson().toJson(queryStudentById))
                }
            }
        }

    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        req.characterEncoding = "UTF-8"
        resp.characterEncoding = "UTF-8"
        val name = req.getParameter("name")
        val score = req.getParameter("score")
        val id = req.getParameter("id")

        when (req.getParameter("type")) {
            "addItem" -> {
                if (name.isNullOrEmpty() || score.isNullOrEmpty() || id.isNullOrEmpty()) {
                    resp.writer.write("请填写完整资料！")
                    return
                }
                studentRepository.addStudent(Student(id.toInt(), name, score.toDouble()))
                resp.writer.write("添加成功")
            }
            "deleteItem" -> {
                if (id.isNullOrEmpty()) {
                    resp.writer.write("请填写完整资料！")
                    return
                }
                if(studentRepository.deleteStudentById(id.toInt()) > 0){
                    resp.writer.write("删除成功")
                }else {
                    resp.writer.write("删除失败")
                }

            }
        }

    }
}