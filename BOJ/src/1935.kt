import java.util.*
import kotlin.math.round

// 후위 표기식2

fun main(){
    val br = System.`in`.bufferedReader()

    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val sp = "*+/-"
    val N = br.readLine().toInt()
    val formula = br.readLine()
    val a = Array(N){0}
    repeat(N){
        a[it] = br.readLine().toInt()
    }
    val stack = Stack<Double>()
    for (i in formula){
        if (sp.contains(i)){
            val first = stack.pop()
            val second = stack.pop()
            when(i){
                '*' -> stack.push(second*first)
                '+' -> stack.push(second+first)
                '-' -> stack.push(second-first)
                '/' -> stack.push(second/first)
            }
        } else {
            stack.push(a[alphabet.indexOf(i)].toDouble())
        }
    }
    var answer = stack.pop()
    answer = round(answer*100)/100
    println(String.format("%.2f", answer))
}