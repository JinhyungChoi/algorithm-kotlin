// 동전 0

fun main(){
    val br = System.`in`.bufferedReader()
    var (N,K) = br.readLine().split(' ').map { it.toInt() }
    val money = ArrayList<Int>()
    repeat(N){
        money.add(br.readLine().toInt())
    }
    money.sortDescending()
    var idx = 0
    var count = 0
    while(K>0){
        if (K < money[idx]){
            idx++
            continue
        } else {
            val q = K / money[idx]
            val remain = K - money[idx]*q
            count += q
            K = remain
            idx ++
        }
    }
    println(count)
}