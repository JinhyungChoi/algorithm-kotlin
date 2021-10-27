fun solution(progresses: IntArray, speeds: IntArray): MutableList<Int> {
    var answer = mutableListOf<Int>()
    var times = mutableListOf<Int>()
    for (i in progresses.indices) {
        val remain = 100 - progresses[i]
        if (remain % speeds[i] == 0) {
            times.add(remain / speeds[i])
        } else {
            times.add(remain / speeds[i] + 1)
        }
    }
    val queue = mutableListOf<Int>()
    queue.add(times[0])
    times.removeAt(0)
    while (queue.isNotEmpty() || times.isNotEmpty()) {
        println("queue:"+queue)
        println("times: $times")
        var count = 1
        if (queue[0] == 0) {
            while (times.isNotEmpty()&&times[0] == 0) {
                times.removeAt(0)
                count += 1
            }
            if (times.isNotEmpty()){
                queue.removeAt(0)
                queue.add(times[0])
                times.removeAt(0)
                answer.add(count)
            } else{
                queue.removeAt(0)
                answer.add(count)
            }
        } else {
            queue[0]--
            for (i in 0 until times.size) {
                if (times[i] > 0) {
                    times[i]--
                } else {
                    continue
                }
            }
        }
    }
    return answer
}
