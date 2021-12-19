import java.util.*
import kotlin.Comparator
import kotlin.math.abs

fun main(){
    val n = readLine()!!.toInt()
    val inputs = mutableListOf<Int>()
    repeat(n){
        inputs.add(readLine()!!.toInt())
    }
    val heap = AbsHeap(AbsComparator())
    inputs.forEach{
        if (it != 0){
            heap.offer(it)
        } else {
            if(heap.isEmpty()){
                println(0)
            } else {
                println(heap.poll())
            }
        }
    }
}

class AbsHeap(comparator: Comparator<Int>) : PriorityQueue<Int>(comparator){
    override fun offer(e: Int?): Boolean {
        return super.offer(e)
    }
}
class AbsComparator : Comparator<Int>{
    override fun compare(o1: Int, o2: Int): Int {
        return when{
            abs(o1) > abs(o2) -> 1
            abs(o1) < abs(o2) -> -1
            else -> {
                if (o1 > o2){
                    1
                } else {
                    -1
                }
            }
        }
    }
}