import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

// 녹색 옷 입은 애가 젤다지?

data class Point(val x: Int, val y: Int, val cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)
    var count = 0
    while (true) {
        count++
        val n = br.readLine().toInt()
        if (n == 0) {
            break
        }
        val cave = Array(n) { ArrayList<Int>() }
        val dist = Array(n) { Array(n) { Int.MAX_VALUE } }
        for (i in 0 until n) {
            val input = br.readLine().split(' ').map { it.toInt() }
            cave[i].addAll(input)
        }

        val pq = PriorityQueue { a: Point, b: Point ->
            when {
                a.cost > b.cost -> 1
                a.cost < b.cost -> -1
                else -> 0
            }
        }

        dist[0][0] = cave[0][0]
        pq.add(Point(0, 0, cave[0][0]))

        while (pq.isNotEmpty()) {
            val now = pq.poll()
            val oX = now.x
            val oY = now.y
            for (i in dx.indices) {
                val nX = oX + dx[i]
                val nY = oY + dy[i]
                if (nX in 0 until n && nY in 0 until n) {
                    if (dist[nX][nY] > dist[oX][oY] + cave[nX][nY]) {
                        dist[nX][nY] = dist[oX][oY] + cave[nX][nY]
                        pq.add(Point(nX, nY, dist[nX][nY]))
                    }
                }
            }
        }
        println("Problem $count: ${dist[n - 1][n - 1]}")
    }
}