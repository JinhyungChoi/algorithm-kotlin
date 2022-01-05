import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.sqrt

//Count Circle Group

data class Bunker(val x: Int, val y: Int, val r: Int, val nodeNum: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()
    repeat(T) {
        val N = br.readLine().toInt()
        val loc = ArrayList<Bunker>()
        val parent = Array(N) { 0 }
        for (i in parent.indices) {
            parent[i] = i
        }
        repeat(N) {
            val (x, y, z) = br.readLine().split(' ').map { it.toInt() }
            loc.add(Bunker(x, y, z, it))
        }
        for (i in loc.indices){
            for (j in i+1 until loc.size){
                if (loc[i].r + loc[j].r >= sqrt(getDistance(loc[i].x, loc[i].y, loc[j].x, loc[j].y))){
                    union(parent, loc[i].nodeNum, loc[j].nodeNum)
                }
            }
        }
        println(parent.toSet().size)
    }
}

fun getDistance(x: Int, y: Int, x1: Int, y1: Int): Double {
    val xDif = x - x1
    val yDif = y - y1
    return (xDif*xDif + yDif*yDif).toDouble()
}


fun union(parent: Array<Int>, value: Int, i: Int) {
    val a = getParent(parent, value)
    val b = getParent(parent, i)
    if (a <= b) {
        parent[i] = a
        find(parent,b,a)
    } else {
        parent[value] = b
        find(parent,a,b)
    }
}

fun find(parent: Array<Int>, b: Int, a: Int) {
    for (i in parent.indices){
        if (parent[i] == b){
            parent[i] = a
        }
    }
}

fun getParent(parent: Array<Int>, p1: Int): Int {
    if (parent[p1] != p1) parent[p1] = getParent(parent, parent[p1])
    return parent[p1]
}
