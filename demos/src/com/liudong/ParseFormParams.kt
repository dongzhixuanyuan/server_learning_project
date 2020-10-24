package gacl.request.study

import com.liudong.BaseServlet
import java.io.IOException
import java.nio.charset.Charset
import java.text.MessageFormat
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.text.Charsets.ISO_8859_1

/**
 * @author gacl
 * 获取客户端通过Form表单提交上来的参数
 */
@WebServlet("/formparse")
class ParseFormParams : BaseServlet() {
    @Throws(ServletException::class, IOException::class)
    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        //客户端是以UTF-8编码提交表单数据的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
        request.characterEncoding = "UTF-8"
        /**
         * 编  号(文本框)：
         * <input type="text" name="userid" value="NO." size="2" maxlength="2"></input>
         */
        val userid =
            request.getParameter("userid") //获取填写的编号，userid是文本框的名字，<input type="text" name="userid">

        /**
         * 用户名(文本框)：<input type="text" name="username" value="请输入用户名"></input>
         * 如果是get方式提示，那么需要加上额外的字符串编码转换代码
         */
        val username = String(request.getParameter("username").toByteArray(ISO_8859_1), Charsets.UTF_8) //获取填写的用户名

        /**
         * 密  码(密码框)：<input type="password" name="userpass" value="请输入密码"></input>
         */
        val userpass = request.getParameter("userpass") //获取填写的密码
        val sex = request.getParameter("sex") //获取选中的性别
        val dept = request.getParameter("dept") //获取选中的部门
        //获取选中的兴趣，因为可以选中多个值，所以获取到的值是一个字符串数组，因此需要使用getParameterValues方法来获取
        val insts = request.getParameterValues("inst")
        val note = request.getParameter("note") //获取填写的说明信息
        val hiddenField = request.getParameter("hiddenField") //获取隐藏域的内容
        var instStr: String? = ""

        /**
         * 获取数组数据的技巧，可以避免insts数组为null时引发的空指针异常错误！
         */
        var i = 0
        while (insts != null && i < insts.size) {
            instStr += if (i == insts.size - 1) {
                insts[i]
            } else {
                insts[i].toString() + ","
            }
            i++
        }
        var htmlStr: String? = "<table>" +
                "<tr><td>填写的编号：</td><td>{0}</td></tr>" +
                "<tr><td>填写的用户名：</td><td>{1}</td></tr>" +
                "<tr><td>填写的密码：</td><td>{2}</td></tr>" +
                "<tr><td>选中的性别：</td><td>{3}</td></tr>" +
                "<tr><td>选中的部门：</td><td>{4}</td></tr>" +
                "<tr><td>选中的兴趣：</td><td>{5}</td></tr>" +
                "<tr><td>填写的说明：</td><td>{6}</td></tr>" +
                "<tr><td>隐藏域的内容：</td><td>{7}</td></tr>" +
                "</table>"
        htmlStr = MessageFormat.format(htmlStr, userid, username, userpass, sex, dept, instStr, note, hiddenField)
        response.characterEncoding = "UTF-8" //设置服务器端以UTF-8编码输出数据到客户端
        response.contentType = "text/html;charset=UTF-8" //设置客户端浏览器以UTF-8编码解析数据
        response.writer.write(htmlStr) //输出htmlStr里面的内容到客户端浏览器显示
    }

    @Throws(ServletException::class, IOException::class)
    public override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }
}