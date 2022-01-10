// 행성 연결

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    data class Edge(val start: Int, val dest: Int, val cost: Int)
    val edges = ArrayList<Edge>()
    val parent = Array(N){0}.apply {
        forEachIndexed{ idx, _ ->
            this[idx] = idx
        }
    }

    repeat(N){
        val input = br.readLine().split(' ').map { it.toInt() }
        for (i in it + 1 until N){
            edges.add(Edge(it, i, input[i]))
        }
    }
    edges.sortBy { it.cost }
    var cost = 0L
    for (edge in edges){
        val st = edge.start
        val de = edge.dest
        if (find(st,parent) == find(de,parent)){
            continue
        } else {
            union(st,de,parent)
            cost += edge.cost
        }
    }
    println(cost)
}
fun union(p1: Int, p2: Int, parent: Array<Int>){
    val a = find(p1, parent)
    val b = find(p2, parent)
    if (a <= b){
        parent[b] = a
    } else {
        parent[a] = b
    }
}
fun find(p: Int, parent: Array<Int>): Int {
    if (parent[p] != p) parent[p] = find(parent[p], parent)
    return parent[p]
}