//별찍기 - 10

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val N = br.readLine().toInt()
    val starMap = Array(N) { Array(N) { "*" } }
    createStar(starMap, N, N, N)
    starMap.forEach {
        it.forEach {
            bw.write(it)
        }
        bw.write("\n")
    }
    bw.close()
}

fun createStar(starmap: Array<Array<String>>, N: Int, x: Int, y: Int) {
    val third = N / 3
    for (i in x - third * 2 until x - third) {
        for (j in y - third * 2 until y - third) {
            starmap[i][j] = " "
        }
    }

    if (third == 1) return

    for (i in 0..2) {
        for (j in 0..2) {
            createStar(starmap, third, x - i * third, y - j * third)
        }
    }
}