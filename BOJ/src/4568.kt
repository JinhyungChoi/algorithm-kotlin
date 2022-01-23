import java.util.*

// LRU Caching
fun main(){
    val br = System.`in`.bufferedReader()
    var count = 0
    while(true){
        count ++
        val simulation = br.readLine()
        if (simulation == "0"){
            break
        }

        println("Simulation $count")

        val (s, cmd) = simulation.split(' ')
        val cacheSize = s.toInt()
        val cache :Queue<Char> = LinkedList()
        for (i in cmd){
            if (i == '!'){
                cache.forEach{
                    print(it)
                }
                println()
            } else {
                if (cache.contains(i)){
                    cache.remove(i)
                } else {
                    if (cache.size >= cacheSize){
                        cache.poll()
                    }
                }
                cache.offer(i)
            }
        }
    }
}