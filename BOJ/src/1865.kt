import kotlin.math.min

//웜홀

fun main(){
    val br = System.`in`.bufferedReader()
    val TC = br.readLine().toInt()

    data class Road(val start: Int, val dest: Int)
    data class Worm(val start: Int, val dest: Int, val cost: Int)

    repeat(TC){
        val (N,M,W) = br.readLine().split(' ').map { it.toInt() }
        val adjList = Array(N+1){ArrayList<Worm>()}

        val roads = mutableMapOf<Road, Int>()
        val worms = ArrayList<Worm>()

        val dist = Array(N+1){Int.MAX_VALUE.toLong()}

        repeat(M){
            val (S,E,T) = br.readLine().split(' ').map { it.toInt() }
            if(roads.contains(Road(S,E))){
                roads[Road(S,E)] = min(roads[Road(S,E)]!!, T)
            } else {
                roads.put(Road(S,E), T)
            }
            if(roads.contains(Road(E,S))){
                roads[Road(E,S)] = min(roads[Road(E,S)]!!, T)
            } else {
                roads.put(Road(E,S), T)
            }
        }
        repeat(W){
            val (S,E,T) = br.readLine().split(' ').map { it.toInt() }
            worms.add(Worm(S,E,T*-1))
        }

        var loop = false
        for (i in 0 until N){
            for (edge in roads){
                if (dist[edge.key.dest] > dist[edge.key.start] + edge.value){
                    dist[edge.key.dest] = dist[edge.key.start] + edge.value
                    if (i == N-1){
                        loop = true
                    }
                }
            }
            for (worm in worms){
                if (dist[worm.dest] > dist[worm.start] + worm.cost){
                    dist[worm.dest] = dist[worm.start] + worm.cost
                    if (i == N-1){
                        loop = true
                    }
                }
            }
        }
        if (loop){
            println("YES")
        } else {
            println("NO")
        }
    }
}