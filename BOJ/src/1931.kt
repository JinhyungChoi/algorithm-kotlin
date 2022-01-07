// 회의실 배정

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    data class Meeting(val start: Int, val end: Int)
    val meetings = ArrayList<Meeting>()

    repeat(N){
        val (st, en) = br.readLine().split(' ').map { it.toInt() }
        meetings.add(Meeting(st,en))
    }
    val sortedMeetings = meetings.sortedWith(
        compareBy(Meeting::end)
            .thenBy (Meeting::start)
    )
    var idx = 0
    var count = 1
    for (i in 1 until meetings.size){
        if (sortedMeetings[i].start >= sortedMeetings[idx].end){
            count += 1
            idx = i
        }
    }
    println(count)
}