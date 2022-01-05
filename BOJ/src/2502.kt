// 떡 먹는 호랑이

fun main() {
    val br = System.`in`.bufferedReader()
    val a1 = arrayListOf(1, 1)
    val a2 = arrayListOf(1, 2)

    val (D, K) = br.readLine().split(' ').map { it.toInt() }
    for (i in 0 until D - 4) {
        a1.add(a1[a1.lastIndex] + a1[a1.lastIndex - 1])
        a2.add(a2[a2.lastIndex] + a2[a2.lastIndex - 1])
    }
    val fDay = a1[D-3]
    val sDay = a2[D-3]
    var fRice = 1
    var sRice = 1
    while (true) {
        if ((K - sRice * sDay) % fDay == 0) {
            val f = (K - sRice * sDay) / fDay
            if (sRice >= f){
                fRice = f
                break
            }
        }
        sRice ++
    }
    println(fRice)
    println(sRice)
}