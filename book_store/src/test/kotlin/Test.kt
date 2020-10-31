import com.liudong.entity.Book
import com.liudong.entity.BookCase
import com.liudong.repository.book.BookRepoImp
import com.liudong.repository.user.LoginRepoImp

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:46 下午
 */
object Test {

    private val repository = BookRepoImp()
    @JvmStatic
    fun main(args: Array<String>) {
        generateBookCase()
    }


    fun generateBook (){
        for (i in 0..500) {
            Book().apply {
                name = (i%128).toChar().toString()
                case
            }
        }
    }

    fun generateBookCase(){
        for (i in 0..9) {
            val case = BookCase().apply {
                category_name = (i % 128 + 62).toChar().toString()
            }
            repository.insertBookCase(case)
        }
    }





}