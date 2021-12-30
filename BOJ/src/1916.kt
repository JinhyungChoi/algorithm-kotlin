import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val cityNum = br.readLine().toInt()
    val busNum = br.readLine().toInt()
    val cityMap = Array(cityNum){Array(cityNum){100_000_001} }
    repeat(busNum){
        val route = br.readLine().split(' ').map{ it.toInt() }
        if (cityMap[route[0]-1][route[1]-1] != 100_000_001){
            cityMap[route[0]-1][route[1]-1] = min(cityMap[route[0]-1][route[1]-1],route[2])
        } else {
            cityMap[route[0] - 1][route[1] - 1] = route[2]
        }
    }
    val question = br.readLine().split(' ')
    val from = question[0].toInt()-1
    val to = question[1].toInt()-1
    val visited = Array(cityNum){false}
    val distance = mutableListOf<Int>()
    distance.addAll(cityMap[from])
    visited[from] = true

    for (i in 0 until cityNum){
        val current = getMinIndex(distance,visited)
        visited[current] = true
        for (j in 0 until cityNum){
            if(!visited[j]){
                if (distance[current] + cityMap[current][j] < distance[j]){
                    distance[j] = distance[current] + cityMap[current][j]
                }
            }
        }
    }
    println(distance[to])
}

fun getMinIndex(list: List<Int>, visited: Array<Boolean>): Int {
    var min = 100_000_000
    var index = 0
    for (i in list.indices){
        if (list[i] < min && !visited[i]){
            min = list[i]
            index = i
        }
    }
    return index
}