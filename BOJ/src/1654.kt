// 랜선 자르기

fun main(){
    val br = System.`in`.bufferedReader()
    val (N,K) = br.readLine().split(' ').map { it.toLong() }
    val lans = mutableListOf<Long>()
    repeat(N.toInt()){
        lans.add(br.readLine().toLong())
    }
    var average = (lans.sum() / K)
    var first = 1L
    var answer = 0L
    while (first <= average){
        val mid = (first + average) / 2
        val list = lans.map { it/mid }
        val s = list.sum()
        if (s < K){
            average = mid -1
        } else{
            first = mid +1
        }
        answer = average
    }
    println(answer)
}