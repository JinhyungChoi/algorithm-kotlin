import java.util.*

fun main() {
    while (true) {
        val input = readLine()!!
        val list = input.split(':') as MutableList<String>
        var answer = ""
        val stack = Stack<String>()
        for (i in list.indices) {
            if (list[i] == "") {
                stack.push("")
            } else {
                val address = makeAddress(list[i], list[i].length)
                stack.push(address)
            }
        }
        var count = 0
        var emptyCount = 0
        while (stack.isNotEmpty()) {
            count += 1

            var a = stack.pop()
            if (a == "" && emptyCount ==0) {
                emptyCount = 1
                list.removeAll(listOf(""))
                println(list)
                for (i in 0 until 8 - list.size) {
                    a = "0000"+a
                    if (i != 7-list.size) {
                        a = ":" + a
                    }
                }
            }
            if (a != "") {
                answer = ":" + a + answer
            }
        }
        println(answer.substring(1))
    }
}

fun makeAddress(add: String, length: Int): String {
    var address = ""

    for (i in 0 until 4 - length) {
        address += "0"
    }
    address += add
    return address
}