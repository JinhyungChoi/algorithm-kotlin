import java.util.*
import kotlin.math.abs
import kotlin.math.round
import kotlin.math.sqrt

// 우주신과의 교감 - MST

fun main(){
    val br = System.`in`.bufferedReader()
    val (N,M) = br.readLine().split(' ').map { it.toInt() }
    data class God(val x: Int, val y: Int, val index: Int)
    data class Edge(val startGod: Int, val destGod: Int, val cost: Double)
    val gods = ArrayList<God>()
    val connected = ArrayList<Pair<God,God>>()
    val adjList = Array(N){ArrayList<Edge>()}

    val connNode = mutableSetOf<Int>()

    repeat(N){
        val (x,y) = br.readLine().split(' ').map { it.toInt() }
        gods.add(God(x,y,it))
    }

    repeat(M){
        val (i,j) = br.readLine().split(' ').map{it.toInt()}
        if(it == 0){
            connected.add(gods[i-1] to gods[j-1])
            connNode.add(i-1)
            connNode.add(j-1)
        } else {
            adjList[i-1].add(Edge(gods[i-1].index, gods[j-1].index, 0.0))
            adjList[j-1].add(Edge(gods[j-1].index, gods[i-1].index, 0.0))
        }
    }

    for (i in 0 until N){
        for (j in 0 until N){
            val startGod = gods[i]
            val destGod = gods[j]
            if (startGod to destGod in connected || destGod to startGod in connected || startGod == destGod){
                continue
            }
            val distance = distanceBetween(startGod.x.toDouble(), startGod.y.toDouble(), destGod.x.toDouble(), destGod.y.toDouble())
            adjList[i].add(Edge(startGod.index, destGod.index, distance))
        }
    }

    val pq = PriorityQueue<Edge>{ a, b ->
        when{
            a.cost > b.cost -> 1
            a.cost < b.cost -> -1
            else -> 0
        }
    }
    for(i in connNode){
        for (j in adjList[i]){
            pq.add(j)
        }
    }

    var c = 0.00

    while(pq.isNotEmpty()){
        val current = pq.poll()
        if (connected.size >= N-1){
            break
        }
        if (current.destGod in connNode){
            continue
        }
        c += current.cost
        connected.add(gods[current.startGod] to gods[current.destGod])
        connNode.add(current.destGod)
        for (i in adjList[current.destGod])
            pq.add(i)
    }
    c = round(c*100)/100
    println(String.format("%.2f",c))
}

fun distanceBetween(x: Double, y: Double, x1: Double, y1: Double): Double {
    val xDiff = abs(x - x1)
    val yDiff = abs(y - y1)
    return sqrt((xDiff * xDiff + yDiff * yDiff).toDouble())
}