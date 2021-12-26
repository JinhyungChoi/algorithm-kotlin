import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val cities = arrayListOf<ArrayList<Int>>()
    repeat(n){
        val input = br.readLine().split(' ')
        val city = input.map{it.toInt()}
        cities.add(city as ArrayList<Int>)
    }
    val input2 = br.readLine().split(' ')
    val plan = input2.map{it.toInt()}
    val dstra = Array(n){Array(n){0} }

    for (i in cities.indices){
        for (j in cities[i].indices){
            if (cities[i][j] == 0 && i != j){
                dstra[i][j] = 10000000
            } else {
                dstra[i][j] = cities[i][j]
            }
        }
    }

    for (k in dstra.indices){
        for (i in dstra.indices){
            for (j in dstra.indices){
                if (i != j) {
                    if (dstra[i][j] > dstra[i][k] + dstra[k][j]) {
                        dstra[i][j] = dstra[i][k] + dstra[k][j]
                    }
                }
            }
        }
    }
    var possible = true
    for (i in 0 until plan.size -1){
        if (dstra[plan[i]-1][plan[i+1]-1] == 10000000){
            possible = false
            break
        }
    }
    if (possible){
        println("YES")
    } else {
        println("NO")
    }
}