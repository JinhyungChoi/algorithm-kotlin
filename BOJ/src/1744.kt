// 수 묶기

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val arr = ArrayList<Int>()
    var zero = 1
    var one = 0
    val neg = ArrayList<Int>()
    val pos = ArrayList<Int>()
    repeat(N){
        arr.add(br.readLine().toInt())
    }
    arr.sortDescending()
    var ans = 0
    if (arr.size > 1){
        for (i in arr){
            if (i == 0){
                zero = 0
            } else if( i == 1){
                one ++
            } else if (i < 0){
                neg.add(i)
            } else {
                pos.add(i)
            }
        }
        neg.sort()
        if (neg.size % 2 == 0 && pos.size % 2 ==0){
            ans += evenNeg(neg) + one + evenPos(pos)
        } else if (neg.size %2 == 1 && pos.size % 2 ==0){
            ans += oddNeg(neg).first + zero*oddNeg(neg).second + one + evenPos(pos)
        } else if (pos.size % 2 == 1 && neg.size % 2 == 0){
            ans += evenNeg(neg) + one + oddPos(pos)
        } else {
            ans += oddNeg(neg).first + zero * oddNeg(neg).second + one + oddPos(pos)
        }
        println(ans)
    } else {
        println(arr[0])
    }
}

fun evenPos(pos: ArrayList<Int>): Int {
    var sum = 0
    for (i in pos.indices){
        if (i%2 == 0){
            sum += pos[i] * pos[i+1]
        }
    }
    return sum
}

fun oddPos(pos: ArrayList<Int>): Int {
    var sum = 0
    for (i in pos.indices){
        if (i%2 == 0){
            sum += if (i == pos.lastIndex){
                pos[i]
            } else {
                pos[i]*pos[i+1]
            }
        }
    }
    return sum
}

fun evenNeg(neg: ArrayList<Int>): Int {
    var sum = 0
    for (i in neg.indices){
        if (i%2 == 0){
            sum += neg[i]*neg[i+1]
        }
    }
    return sum
}

fun oddNeg(neg:ArrayList<Int>): Pair<Int, Int> {
    var sum = 0
    val remaining = neg.last()

    for (i in neg.indices){
        if (i%2 == 0 && i != neg.lastIndex){
            sum += neg[i] * neg[i+1]
        }
    }
    return sum to remaining
}