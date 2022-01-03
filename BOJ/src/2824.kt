import java.io.BufferedReader
import java.io.InputStreamReader

// 최대 공약수
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    val nNums = br.readLine().split(' ').map { it.toInt() }.toMutableList()
    val m = br.readLine().toInt()
    val mNums = br.readLine().split(' ').map { it.toInt() }.toMutableList()
    val max = 1_000_000_000

    var flag = false
    var answer = 1L
    for (i in nNums.indices){
        if (nNums[i] == 1) continue
        for (j in mNums.indices){
            if (mNums[j] == 1) continue
            val g = gcd(nNums[i], mNums[j]).toLong()
            answer *= g
            if (answer >= max) flag = true
            answer %= max
            nNums[i] /= g.toInt()
            mNums[j] /= g.toInt()
        }
    }
    if (flag){
        val sAnswer = answer.toString()
        var a = ""
        for (i in 0 until 9 - sAnswer.length){
            a += "0"
        }
        a += sAnswer
        println(a)
    } else {
        println(answer)
    }
}

fun gcd(a: Int, b: Int): Int {
    if (b == 0){
        return a
    }
    return gcd(b , a%b)
}