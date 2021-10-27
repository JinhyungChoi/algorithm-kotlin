fun solution3(bridge_length: Int, weight: Int, truck_weights: IntArray): Int{
    var answer = 1
    val bridge = mutableListOf<Int>()
    for (i in 0 until bridge_length){
        bridge.add(0)
    }
    val completed = mutableListOf<Int>()
    val truckqueue = mutableListOf<Int>()
    truck_weights.forEach { truckqueue.add(it) }
    bridge.removeAt(0)
    bridge.add(truckqueue.first())
    truckqueue.removeAt(0)
    while(completed.size!= truck_weights.size){
        println(bridge)
        answer ++
        val front = bridge.removeAt(0)
        if(front!=0){
            completed.add(front)
        }
        if(truckqueue.isNotEmpty()){
            if(truckqueue.first() + bridge.sum() <= weight) {
                bridge.add(truckqueue.first())
                truckqueue.removeAt(0)
            } else{
                bridge.add(0)
            }
        }
    }
    return answer
}
