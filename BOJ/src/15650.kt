import java.io.BufferedReader
import java.io.InputStreamReader

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val input = br.readLine().split(' ')
    val n = input[0].toInt()
    val m = input[1].toInt()
    findSet(0,ArrayList<Int>(), n, m)
}
fun findSet(start: Int, arr: ArrayList<Int>, n: Int, m: Int){
    if (arr.size == m){
        arr.forEach{
            print("$it ")
        }
        println()
    }
    for (i in start until n){
        val arrList = ArrayList<Int>()
        arrList.addAll(arr)
        arrList.add(i+1)
        findSet(i+1, arrList, n, m)
    }
}