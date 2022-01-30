import java.util.*
import kotlin.collections.ArrayList

// 선수 과목 (Prerequisite)

fun main(){
    val br = System.`in`.bufferedReader()
    val (N,M) = br.readLine().split(' ').map { it.toInt() }
    val prerequisite = Array(N){ArrayList<Int>() }
    val adjList = Array(N){ArrayList<Int>()}
    data class Node (val subject: Int, val semester: Int)

    repeat(M){
        val (A,B) = br.readLine().split(' ').map { it.toInt() }
        prerequisite[B-1].add(A-1)
        adjList[A-1].add(B-1)
    }
    val entry = prerequisite.map { it.size }.toMutableList()
    val queue : Queue<Node> = LinkedList()
    for (i in entry.indices){
        if (entry[i] == 0){
            queue.add(Node(i, 1))
        }
    }
    val answer = Array(N){1}
    while (queue.isNotEmpty()){
        val curr = queue.poll()
        val cSub = curr.subject
        val cSem = curr.semester
        for (i in adjList[cSub]){
            entry[i]--
            if (entry[i] == 0){
                queue.add(Node(i, cSem+1))
            }
        }
        answer[cSub] = cSem
    }
    answer.forEach{
        print("$it ")
    }
}