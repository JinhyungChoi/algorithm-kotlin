// 내려가기

fun main() {
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val scores = ArrayList<List<Int>>()

    repeat(N) {
        val nums = br.readLine().split(' ').map { it.toInt() }
        scores.add(nums)
    }

    val maxVisited = Array(N) { Array(3) { false } }

    val maxDp = Array(N) { Array(3) { 0 } }

    fun maxDfs(x: Int, y: Int): Int {
        if (maxVisited[x][y]) {
            return maxDp[x][y]
        }
        if (x == N - 1) {
            maxDp[x][y] = scores[x][y]
            return scores[x][y]
        }
        if (x + 1 < N) {
            if (y == 0) {
                maxDp[x][y] += scores[x][y]+ maxOf(maxDfs(x + 1, 0), maxDfs(x+1,1))
            } else if (y == 1) {
                maxDp[x][y] += scores[x][y]+maxOf(maxDfs(x + 1, 0), maxDfs(x+1,1), maxDfs(x+1,2))
            } else {
                maxDp[x][y] += scores[x][y]+maxOf(maxDfs(x + 1, 1), maxDfs(x + 1, 2))
            }
        }
        maxVisited[x][y] = true
        return maxDp[x][y]
    }

    val minVisited = Array(N) { Array(3) { false } }
    val minDp  = Array(N) { Array(3) { 0 } }

    fun minDfs(x: Int, y: Int): Int {
        if (minVisited[x][y]) {
            return minDp[x][y]
        }
        if (x == N - 1) {
            minDp [x][y] = scores[x][y]
            return scores[x][y]
        }
        if (x + 1 < N) {
            if (y == 0) {
                minDp[x][y] += scores[x][y]+ minOf(minDfs(x + 1, 0), minDfs(x+1,1))
            } else if (y == 1) {
                minDp[x][y] += scores[x][y]+minOf(minDfs(x + 1, 0), minDfs(x+1,1), minDfs(x+1,2))
            } else {
                minDp[x][y] += scores[x][y]+minOf(minDfs(x + 1, 1), minDfs(x + 1, 2))
            }
        }
        minVisited[x][y] = true
        return minDp[x][y]
    }

    maxDfs(0, 0)
    maxDfs(0, 1)
    maxDfs(0, 2)
    minDfs(0,0)
    minDfs(0,1)
    minDfs(0,2)
    println("${maxDp.get(0).maxOrNull()} ${minDp.get(0).minOrNull()}")

}