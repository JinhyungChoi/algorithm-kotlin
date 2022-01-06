import java.util.*

// 내리막 길
fun main() {

    val br = System.`in`.bufferedReader()
    val (N, M) = br.readLine().split(' ').map { it.toInt() }
    val tMap = Array(N) { Array(M) { 0 } }
    repeat(N) {
        val input = br.readLine().split(' ').map { it.toInt() }
        for (i in input.indices) {
            tMap[it][i] = input[i]
        }
    }
    val visited = Array(N) { Array(M) { false } }

    val dp = Array(N){Array(M){0} }

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    fun dfs(x: Int, y: Int) : Int{
        if(visited[x][y]){
            return dp[x][y]
        }
        if (x == N-1 && y == M-1){
            return 1
        }
        for (i in dx.indices){
            val nX = x + dx[i]
            val nY = y + dy[i]
            if (nX in 0 until N && nY in 0 until M && tMap[x][y] > tMap[nX][nY]) {
                dp[x][y] += dfs(nX,nY)
            }
        }
        visited[x][y] = true
        return dp[x][y]
    }
    dfs(0,0)
    println(dp[0][0])
}

