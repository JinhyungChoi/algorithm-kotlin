import java.util.*
import kotlin.collections.ArrayList

// 최단 경로

fun main(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    data class Edge(val dest: Int, val cost: Int)

    val (V,E) = br.readLine().split(' ').map { it.toInt() }
    val K = br.readLine().toInt()
    val adjList = Array(V){ArrayList<Edge>()}
    val INF = Int.MAX_VALUE
    val dist = Array(V){INF}

    repeat(E){
        val (u,v,w) = br.readLine().split(' ').map{it.toInt()}
        adjList[u-1].add(Edge(v, w))
    }

    val pq = PriorityQueue<Edge>{ a, b ->
        when{
            a.cost > b.cost -> 1
            a.cost < b.cost -> -1
            else -> 0
        }
    }

    dist[K-1] = 0
    pq.add(Edge(K,0))
    while(pq.isNotEmpty()){
        val current = pq.poll()
        if (dist[current.dest-1] < current.cost){
            continue
        }
        for (edge in adjList[current.dest-1]){
            val nCost = current.cost + edge.cost
            if (nCost >= dist[edge.dest-1]){
                continue
            }
            dist[edge.dest-1] = nCost
            pq.add(Edge(edge.dest, nCost))
        }
    }
    dist.forEach{
        if(it==INF){
            bw.write("INF\n")
        } else {
            bw.write("$it\n")
        }
    }
    bw.close()
}