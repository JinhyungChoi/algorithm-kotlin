import java.util.*

// 안정적인 문자열

fun main(){
    val br = System.`in`.bufferedReader()
    var TC = 0
    while (true){
        TC ++
        val input = br.readLine()
        if (input.contains("-")){
            break
        }
        var ans = 0
        val stack = Stack<Char>()
        input.forEach{
            if (it == '{'){
                stack.push(it)
            } else {
                if (stack.isEmpty()){
                    stack.add('{')
                    ans ++
                } else {
                    stack.pop()
                }
            }
        }
        if (stack.isNotEmpty()){
            ans += stack.size/2
        }

        println("$TC. $ans")
    }
}