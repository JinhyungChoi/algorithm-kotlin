import java.io.BufferedWriter
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.floor

fun main(){
    val input1 = readLine()!!

    val n = input1.split(' ')[0].toInt()
    var l = input1.split(' ')[1].toInt()

    var answer = ""

    for (i in l .. 101){
        if(i == 101){
            println("-1")
            break
        }
        if (n%2 == 0 && i == 2){
            continue
        } else if (n%2 != 0 && i == 2){
            println("${n/i} ${(n/i)+1}")
            break

        } else {
            val d = n/i
            val from = d - floor((i-1)/2F).toInt()
            val to = d + ceil((i-1)/2F).toInt()
            var sum = 0
            if (from >= 0){
                for (j in from .. to){
                    sum += j
                }
            }
            if (sum == n){
                val bw = BufferedWriter(OutputStreamWriter(System.out))
                for (j in from .. to){
                    answer += j
                    answer += " "
                }
                answer = answer.substringBeforeLast(' ')
                bw.write(answer)
                bw.flush()
                bw.flush()
                bw.close()
                break
            }
        }
    }
}