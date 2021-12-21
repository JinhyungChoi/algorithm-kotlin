import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = readLine()!!

    val n = input.split(' ')[0].toInt()
    val m = input.split(' ')[1].toInt()

    val maze = arrayListOf<ArrayList<Int>>()
    val visited = arrayListOf<ArrayList<ArrayList<Boolean>>>()
    val v = arrayListOf<ArrayList<Boolean>>()

    val v2 = ArrayList<ArrayList<Boolean>>()
    repeat(n) {
        val array = ArrayList<Int>()
        val vArray = ArrayList<Boolean>()
        val v2Array = ArrayList<Boolean>()
        val line = readLine()!!
        line.forEach {
            array.add(it.toString().toInt())
            vArray.add(false)
            v2Array.add(false)
        }
        maze.add(array)
        v.add(vArray)
        v2.add(v2Array)
    }
    visited.add(v)
    visited.add(v2)
    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, 1, 0, -1)

    visited[0][0][0] = true
    val queue: Queue<CMaze> = LinkedList()
    queue.offer(CMaze(0, 0, 1, 0))
    while (queue.isNotEmpty()) {
        val cMaze = queue.poll()
        if (cMaze.x == m - 1 && cMaze.y == n - 1) {
            println(cMaze.length)
            break
        }
        for (i in 0 until 4) {
            val newX = cMaze.x + dx[i]
            val newY = cMaze.y + dy[i]
            if (newX >= m || newX < 0 || newY >= n || newY < 0) {
                continue
            }
            if (cMaze.breakWall == 1) {
                if (visited[1][newY][newX] || maze[newY][newX] == 1) {
                    continue
                }
                visited[1][newY][newX] = true
                queue.offer(CMaze(newX, newY, cMaze.length + 1, 1))
            } else {
                if (visited[0][newY][newX]) {
                    continue
                }
                visited[maze[newY][newX] - 0][newY][newX] = true
                queue.offer(CMaze(newX, newY, cMaze.length + 1, maze[newY][newX] - 0))
            }
        }
        if (queue.isEmpty()) {
            println("-1")
        }
    }
}

data class CMaze(val x: Int, val y: Int, val length: Int, val breakWall: Int)