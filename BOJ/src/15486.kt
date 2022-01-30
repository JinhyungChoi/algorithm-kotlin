import java.lang.Integer.max

// 퇴사 2

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val T = Array(N){0}
    val P = Array(N){0}
    repeat(N){
        val (t,p) = br.readLine().split(' ').map { it.toInt() }
        T[it] = t
        P[it] = p
    }
    val dp = Array(N+2){0}
    var m = 0
    for (i in 1 until dp.size-1){
        m = max(m, dp[i])
        if (i + T[i-1] > N+1){
            continue
        }
        dp[i+T[i-1]] =  max(m + P[i-1], dp[i+T[i-1]])

    }
    print(dp.maxOrNull())
}