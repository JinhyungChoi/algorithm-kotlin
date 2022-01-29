// 랜선 자르기

fun main() {
    val br = System.`in`.bufferedReader()
    val (N, K) = br.readLine().split(' ').map { it.toLong() }
    val lans = mutableListOf<Long>()
    repeat(N.toInt()) {
        lans.add(br.readLine().toLong())
    }
    var average = (lans.sum() / K)
    var first = 1L
    var answer = 0L
    while (first <= average) {
        val mid = (first + average) / 2
        lans.sumOf { it / mid }.let {
            if (it < K) {
                average = mid - 1
            } else {
                first = mid + 1
            }
        }
        answer = average
    }
    println(answer)
}