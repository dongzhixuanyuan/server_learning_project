import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/13 9:25 下午
 */
class AutoWiredTest {
    var talk: Talk? = null


    var age: Int? = null
        @Required set
}


class Talk {

}

class Dog {


    @Autowired  var talk: Talk? = null


    @Autowired
    fun testTalk() {
        println(talk)
    }
    fun initTest(){
        println("initTest")
    }

}

class Cat  @Autowired constructor(var age:Int) {


    @PostConstruct fun initMethod (){
        println("Cat init")
    }

    @PreDestroy fun onDestroy(){
        println("Cat Destroy")
    }


}











