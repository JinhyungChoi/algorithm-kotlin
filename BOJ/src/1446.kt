import kotlin.math.min

//지름길

fun main() {
    val br = System.`in`.bufferedReader()
    val (N, D) = br.readLine().split(' ').map { it.toInt() }
    data class Edge(val start: Int, val dest: Int, val cost: Int)

    val edges = ArrayList<Edge>()
    val dist = Array(D + 1) { 0 }
    dist.forEachIndexed { index, i ->
        dist[index] = index
    }
    repeat(N) {
        val (s, e, c) = br.readLine().split(' ').map { it.toInt() }
        edges.add(Edge(s, e, c))
    }

    for (j in 1 until dist.size) {
        dist[j] = min(dist[j], dist[j - 1] + 1)
        for (i in edges.indices) {
            val curr = edges[i]
            if (curr.dest > D) {
                continue
            }
            dist[curr.dest] = min(dist[curr.dest], dist[curr.start] + curr.cost)
        }
    }
    println(dist[D])
}