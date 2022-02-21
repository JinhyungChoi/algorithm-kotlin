import java.util.*
import kotlin.collections.ArrayList

// 택배

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(' ').map { it.toInt() }
    val djikstra = Array(n) { Array(n) { Int.MAX_VALUE.toLong() } }
    val answer = Array(n) { Array(n) { "-" } }
    data class Node (val dest: Int, val cost: Long)
    val adjList = Array(n+1){ArrayList<Node>()}

    repeat(m){
        val (s,d,c) = br.readLine().split(' ').map { it.toInt() }
        adjList[s].add(Node(d,c.toLong()))
        adjList[d].add(Node(s,c.toLong()))
    }

    val pq = PriorityQueue { a: Pair<Node,Int>, b: Pair<Node,Int> ->
        when {
            a.first.cost > b.first.cost -> 1
            b.first.cost > a.first.cost -> -1
            else -> 0
        }
    }
    repeat(n){
        pq.clear()
        adjList[it+1].forEach{ node ->
            pq.add(node to node.dest)
            djikstra[it][node.dest-1] = node.cost
            answer[it][node.dest-1] = node.dest.toString()
        }
        djikstra[it][it] = 0
        while (pq.isNotEmpty()){
            val curr = pq.poll()
            if (curr.first.cost > djikstra[it][curr.first.dest-1]) continue
            val list = adjList[curr.first.dest]
            for (edge in list){
                val nDist = curr.first.cost + edge.cost
                if (djikstra[it][edge.dest-1] > nDist){
                    djikstra[it][edge.dest-1] = nDist
                    pq.add(Node(edge.dest,nDist) to curr.second)
                    if (curr.second == it+1){
                        answer[it][edge.dest-1] = edge.dest.toString()
                    } else {
                        answer[it][edge.dest-1] = curr.second.toString()
                    }
                }
            }
        }

    }
    answer.forEach{
        it.forEach{
            print("$it ")
        }
        println()
    }
}