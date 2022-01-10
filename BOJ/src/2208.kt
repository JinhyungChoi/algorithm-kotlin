import java.lang.Integer.max

// 보석 줍기

fun main() {
    val br = System.`in`.bufferedReader()

    val (N, M) = br.readLine().split(' ').map { it.toInt() }
    val maze = Array(N) { 0 }
    repeat(N) {
        maze[it] = br.readLine().toInt()
    }
    val pSum = Array(N){0}

    pSum[0] = maze[0]
    repeat(N-1) {
        pSum[it+1] = pSum[it] + maze[it+1]
    }
    val dp = Array(N){0}
    dp[M-1] = pSum[M-1]
    for (i in M until N){
        dp[i] = max(dp[i-1]+maze[i], pSum[i]- pSum[i-M])
    }
    var answer =dp.slice(M until N).maxOrNull()
    if (answer == null){
        answer = dp.maxOrNull()!!
        if (answer < 0){
            answer = 0
        }
    } else if (answer < 0){
        answer = 0
    }
    println(answer)
}

