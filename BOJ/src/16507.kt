// 어두운 건 무서워 (누적 합)

fun main(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (R,C,Q) = br.readLine().split(' ').map { it.toInt() }
    val photo = ArrayList<List<Int>>()
    val dp = Array(R){Array(C){0} }
    val realdp = Array(R){Array(C){0} }

    repeat(R){
        photo.add(br.readLine().split(' ').map { it.toInt() })
    }

    // 사각형 누적합
    for (i in photo.indices){
        var sum = 0
        for (j in photo[0].indices){
            sum += photo[i][j]
            dp[i][j] = sum
        }
    }
    for (i in photo[0].indices){
        var sum = 0
        for (j in photo.indices){
            sum += dp[j][i]
            realdp[j][i] = sum
        }
    }

    repeat(Q){
        val (r1,c1, r2,c2) = br.readLine().split(' ').map { it.toInt() }
        var minusSum1 = 0
        var minusSum2 = 0
        var plusSum = 0
        val totalSum = realdp[r2-1][c2-1]
        if (r1 >= 2){
            minusSum1 = realdp[r1-2][c2-1]
        }
        if (c1 >= 2){
            minusSum2 = realdp[r2-1][c1-2]
        }
        if (r1 >= 2 && c1 >= 2) {
            plusSum = realdp[r1 - 2][c1 - 2]
        }
        bw.write("${(totalSum - minusSum1 - minusSum2 + plusSum)/((r2-r1+1)*(c2-c1+1))}\n")
    }
    bw.close()
}