package kakao
class KakaoBlindRecruitmentParking{

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val answer = mutableListOf<Int>()
        val map = hashMapOf<String, Int>()
        val parkingLot = hashMapOf<String,Int>()
        val sTime = fees[0]
        val sPrice = fees[1]
        val pTime = fees[2]
        val pPrice = fees[3]

        records.forEach{ record ->
            val (time, num, status) = record.split(' ')
            val (h, m) = time.split(':').map { it.toInt() }
            val t = h*60 + m
            if (parkingLot.containsKey(num)){
                val inTime = parkingLot[num]!!
                val diff = t - inTime
                if (map.containsKey(num)){
                    map[num] = map[num]!! + diff
                } else {
                    map[num] = diff
                }
                parkingLot.remove(num)
            } else {
                parkingLot.put(num, t)
            }
        }
        if(parkingLot.size > 0){
            parkingLot.forEach{
                val num = it.key
                val time = it.value

                if (map.containsKey(num)){
                    map[num] = map[num]!! + (1439-time)
                } else {
                    map[num] = 1439-time
                }
            }
        }
        val keys = map.keys.sorted()

        for (i in keys.indices){
            val time = map[keys[i]]!!
            val diff = (time - sTime).coerceAtLeast(0)
            val fee = if (diff%pTime != 0){
                diff / pTime + 1
            } else {
                diff / pTime
            }
            answer.add(sPrice + fee * pPrice)
        }
        return answer.toIntArray()
    }
}

fun main(){
    val a = KakaoBlindRecruitmentParking()
    // TC #1
    var fees = intArrayOf(180, 5000, 10, 600)
    var records = arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT")
    println(a.solution(fees,records))
    // TC #2
    fees = intArrayOf(120,0,60,591)
    records = arrayOf("16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN")
    println(a.solution(fees,records))

    //TC #3
    fees = intArrayOf(1,461,1,10)
    records = arrayOf("00:00 1234 IN")
    println(a.solution(fees,records))
}

