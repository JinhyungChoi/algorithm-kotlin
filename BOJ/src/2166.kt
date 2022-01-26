import kotlin.math.abs

// 다각형의 면적

fun main(){
    val br = System.`in`.bufferedReader()

    val N = br.readLine().toInt()

    val points = Array(N){Array(2){0L} }
    repeat(N){
        val (x,y) = br.readLine().split(' ').map { it.toLong() }
        points[it][0] = x
        points[it][1] = y
    }
    var sumX = 0.0
    var sumY = 0.0
    for (i in 0 until points.size-1){
        sumX += points[i][0] * points[i+1][1]
        sumY += points[i][1] * points[i+1][0]
    }
    sumX += points[points.size-1][0] * points[0][1]
    sumY += points[0][0] * points[points.size-1][1]
    val result = abs(sumX - sumY)/2
    println(String.format("%.1f", result))

}