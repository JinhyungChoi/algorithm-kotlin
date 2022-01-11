import java.util.*
import kotlin.collections.ArrayList

// Puyo Puyo

fun main() {
    val br = System.`in`.bufferedReader()
    val game = ArrayList<ArrayList<String>>()

    val bfsQueue : Queue<Pair<Int,Int>> = LinkedList()
    val visited = Array(12) { Array(6) { false } }
    val block = ArrayList<Pair<Int,Int>>()
    val blocks = ArrayList<ArrayList<Pair<Int,Int>>>()
    var ans = 0

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0,0,1,-1)

    repeat(12) {
        game.add(arrayListOf())
        val line = br.readLine()
        for (i in line) {
            game[it].add(i.toString())
        }
    }

    fun printGame() {
        println("------------Game--------------")
        game.forEach {
            println(it)
        }
    }

    fun bfs(x: Int, y:Int){
        bfsQueue.offer(x to y)
        block.add(x to y)
        visited[x][y] = true
        while (bfsQueue.isNotEmpty()){
            val current = bfsQueue.poll()
            val a = current.first
            val b = current.second
            for (i in dx.indices){
                val nX = a + dx[i]
                val nY = b + dy[i]
                if (nX in 0 until 12 && nY in 0 until 6){
                    if (game[nX][nY] == game[a][b] && !visited[nX][nY]){
                        bfsQueue.offer(nX to nY)
                        block.add(nX to nY)
                        visited[nX][nY] = true
                    }
                }
            }
        }
    }

    fun resetVisited(){
        for (i in visited){
            for (j in i.indices){
                i[j] = false
            }
        }
    }

    fun resetBoard(){
        for (i in blocks){
            for (j in i)
            game[j.first][j.second] = "."
        }
        blocks.clear()
        for (i in game.size-2 downTo 0){
            for (j in game[i].indices){
                if (game[i][j] != "." && game[i+1][j] == "."){
                    var x = i
                    while (x < game.size-1){
                        if (game[x+1][j] == "."){
                            val temp = game[x][j]
                            game[x][j] = "."
                            game[++x][j] = temp
                        } else {
                            break
                        }
                    }
                }
            }
        }
    }

    while (true){
        var b = false

        for (i in game.size-1 downTo 0){
            for (j in game[i].indices){
                if (game[i][j] == "."){
                    continue
                }
                if (!visited[i][j]){
                    bfs(i, j)
                    if (block.size >= 4){
                        val temp = ArrayList<Pair<Int,Int>>()
                        temp.addAll(block)
                        blocks.add(temp)

                        block.clear()
                        b = true
                    } else {
                        block.clear()
                    }
                }
            }
        }
        if (b){
            ans += 1
            resetBoard()
            resetVisited()
        }

        if (!b){
            break
        }
    }
    println(ans)
}