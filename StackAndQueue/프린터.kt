fun solution2(priorities: IntArray, location: Int): Int {
    var answer = 0

    val queue = mutableListOf<Pair<Int,Int>>()
    priorities.forEachIndexed { index, i ->
        queue.add(Pair(i,index))
    }
    var i = 0
    var j: Int

    while(i != queue.size-1){
        j = i+1
        while(j < queue.size-1){
            if(queue[i].first< queue[j].first) {
                val pair = queue[i]
                queue.removeAt(i)
                queue.add(pair)
                j = i+1
            } else {
                j++
            }
        }
        i++
    }
    queue.forEachIndexed { index, pair ->
        if(pair.second == location){
            answer = index+1
        }
    }
    return answer
}
