import java.util.*
import kotlin.collections.HashMap

fun main() {
    while (true) {
        val input = readLine()!!
        val n = input.split(' ')[0].toInt()
        val m = input.split(' ')[1].toInt()
        val el = 0 //0000000000
        var al = 0 //1111111111
        var od = 0 //1010101010
        var sp = 0 //1001001001
        if (n <= 10) {
            for (i in 0 until n) {

                if (i % 2 == 0) {
                    al = al shl 1
                    od = od shl 1
                    al = al or 1
                    od = od or 1
                } else {
                    al = al shl 1
                    al = al or 1
                    od = od shl 1
                }
                if ((i + 1) % 3 == 1) {
                    sp = sp shl 1
                    sp = sp or 1
                } else {
                    sp = sp shl 1
                }
            }
        } else {
            for (i in 0 until 10) {

                if (i % 2 == 0) {
                    al = al shl 1
                    od = od shl 1
                    al = al or 1
                    od = od or 1
                } else {
                    al = al shl 1
                    al = al or 1
                    od = od shl 1
                }
                if ((i + 1) % 3 == 1) {
                    sp = sp shl 1
                    sp = sp or 1
                } else {
                    sp = sp shl 1
                }
            }
        }
        val ev = od shr 1
        val map = HashMap<Int, Int>()
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        val ways = listOf(al, od, ev, sp)
        val time = listOf(n, n / 2 + 1, n / 2, (n - 1) / 3 + 1)
        queue.offer(Pair(el, m))

        while (queue.isNotEmpty()) {
            val state = queue.poll()
            val eState = state.first
            val rTime = state.second
            map.put(state.first, 1)
            for (i in ways.indices) {
                val a = eState xor ways[i]
                val time = rTime - time[i]
                if (!map.containsKey(a) && time >= 0) {
                    queue.offer(Pair(a, time))
                }
            }
        }
        println(map.size)
    }
}

