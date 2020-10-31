import com.liudong.repository.LoginRepoImp

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:46 下午
 */
object Test {


    @JvmStatic
    fun main(args: Array<String>) {
        val login = LoginRepoImp().login("张三", "123")
        assert(login != null)
    }

}