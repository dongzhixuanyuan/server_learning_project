import java.util.*

/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/8 6:21 下午
 */


class TextEditor {
    var spellChecker: SpellChecker

    constructor(spellChecker: SpellChecker) {
        this.spellChecker = spellChecker
    }

    fun spellCheck() {
        spellChecker.checkSpell()
    }
}

class SpellChecker {
    fun checkSpell(){
        println("checking spell")
    }
}

class Student {
    var speaker:Speaker? = null
    set(value) {
        println("Insider setSpeaker")
    }

    var name:String? = null
    set(value) {
        println("Insider setName $value")
    }

    fun speak(){
        speaker?.speak()
    }

    var writer:Writer? = null

    fun write() {
        writer?.write()
    }


}

class Speaker {
    fun speak(){
        println("Speaker speak")
    }
}

class Writer {
    fun write(){
        println("Writer write")
    }
}


class JavaCollection {
    var addressList: List<*>? = null
    var addressSet: Set<*>? = null
    var addressMap: Map<*, *>? = null
    var addressProp: Properties? = null
}

class Address {

}
