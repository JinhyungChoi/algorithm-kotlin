import java.util.*

// 옥상 정원 꾸미기

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val buildings = Array(N){0}
    repeat(N){ i ->
        buildings[i] = br.readLine().toInt()
    }
    val stack = Stack<Pair<Int,Long>>()
    val popped = mutableListOf<Long>()
    stack.add(buildings[0] to 0)

    for (i in 1 until buildings.size){
        val current = stack.pop()
        val next = buildings[i]

        if (current.first > next){
            stack.add(current.first to current.second + 1)
            stack.add(next to 0)
        } else {
            var front = 0L
            popped.add(current.second)
            while (stack.isNotEmpty()){
                val t = stack.pop()
                if (t.first > next){
                    stack.add(t.first to t.second + front + 1)
                    stack.add(next to 0)
                    break
                } else {
                    popped.add(t.second + front)
                    front += t.second
                }
                if (stack.isEmpty()){
                    stack.add(next to 0)
                    break
                }
            }
            if (stack.isEmpty()){
                stack.add(next to 0)
            }
        }
    }
    var sum = 0L
    var stackSum = 0L
    stack.reversed().forEach{
        sum += it.second + stackSum
        stackSum += it.second
    }
    sum += popped.sum()
    println(sum)
}