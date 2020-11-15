package annotation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.stereotype.Component
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


    @Autowired
    var talk: Talk? = null


    @Autowired
    fun testTalk() {
        println(talk)
    }

    fun initTest() {
        println("initTest")
    }

}

class Cat @Autowired constructor(var age: Int) {


    @PostConstruct
    fun initMethod() {
        println("annotation.Cat init")
    }

    @PreDestroy
    fun onDestroy() {
        println("annotation.Cat Destroy")
    }


}

//如何注入一个list
@Component
class InjectListDemo {
    @Autowired
    var animalList: List<Animal>? = null
}

@Component
open class Animal {

}

@Component
class Cow : Animal() {

}

@Component
class Fish : Animal() {

}

@Component
class Pig : Animal() {}











