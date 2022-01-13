//타임머신

fun main(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    data class Edge (val start: Int, val dest: Int, val cost: Int)

    val (N,M) = br.readLine().split(' ').map { it.toInt() }
    val edges = ArrayList<Edge>()

    repeat(M){
        val (A,B,C) = br.readLine().split(' ').map { it.toInt() }
        edges.add(Edge(A,B,C))
    }

    var neg = false
    val dist = Array(N){Int.MAX_VALUE.toLong()}
    dist[0] = 0

    for (i in 0 until N){
        for (edge in edges){
            if (dist[edge.start-1] == Int.MAX_VALUE.toLong()){
                continue
            }
            if (dist[edge.dest-1] > dist[edge.start-1] + edge.cost){
                dist[edge.dest-1] = dist[edge.start-1] + edge.cost
                if (i == N-1){
                    neg = true
                }
            }
        }
    }

    if (neg){
        println("-1\n")
    } else{
        dist.slice(1 until dist.size).forEach{
            if(it != Int.MAX_VALUE.toLong()){
                bw.write("$it\n")
            } else {
                bw.write("-1\n")
            }
        }
    }
    bw.flush()
    bw.close()
}