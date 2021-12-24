import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val input = br.readLine().toInt()
    var set = 0
    val maskingBit = (1 shl 20)-1
    repeat(input) {
        val input2 = br.readLine().split(' ')
        val cmd: String
        var idx = 0
        if (input2.size > 1){
            cmd = input2[0]
            idx = input2[1].toInt()
        } else {
            cmd = input2[0]
        }
        when (cmd) {
            "add" -> {
                set = set or (1 shl idx -1)
            }
            "remove" -> {
                val a = maskingBit xor (1 shl idx -1)
                set = set and a
            }
            "check" -> {
                if ((set and (1 shl idx-1)) == 0){
                    bw.write("0\n")
                } else {
                    bw.write("1\n")
                }
            }
            "toggle" -> {
                set = set nand idx
            }
            "all" -> {
                set = set or maskingBit
            }
            "empty" -> {
                set = 0
            }
        }
    }
    bw.close()
    br.close()

}

infix fun Int.nand(a: Int): Int{
    val maskingBit = (1 shl 20)-1
    return if (this and (maskingBit xor (1 shl a -1)) != this){ // 있는 경우
        this and (maskingBit xor (1 shl a -1))
    } else {
        this or (1 shl a -1)
    }
}

