import kotlin.math.pow

// 톱니바퀴

fun main() {
    val br = System.`in`.bufferedReader()
    val gears = arrayListOf<Gear>()
    repeat(4) {
        val input = br.readLine().toList().map { it.toString().toInt() }
        gears.add(Gear(input[0], input[1], input[2], input[3], input[4], input[5], input[6], input[7]))
    }
    for (i in gears.indices) {
        if (i == 0) {
            gears[i].rightGear = gears[i + 1]
        } else if (i == 3) {
            gears[i].leftGear = gears[i - 1]
        } else {
            gears[i].leftGear = gears[i - 1]
            gears[i].rightGear = gears[i + 1]
        }
    }

    val TC = br.readLine().toInt()

    repeat(TC) {
        val (idx, way) = br.readLine().split(' ').map { it.toInt() }
        if (way == -1) {
            gears[idx - 1].rotateCounterClock()
        } else {
            gears[idx - 1].rotateClock()
        }
        for (i in gears) {
            i.hasRotated = false
        }
    }
    var ans = 0.0
    for (i in gears.indices) {
        ans += gears[i].N * 2.0.pow(i)
    }
    println(ans.toInt())
}

class Gear(var N: Int, var NW: Int, var W: Int, var SW: Int, var S: Int, var SE: Int, var E: Int, var NE: Int) {
    var leftGear: Gear? = null
    var rightGear: Gear? = null
    var hasRotated = false

    fun rotateClock() {
        hasRotated = true
        if (leftGear != null && rightGear != null) {
            if (this.E == leftGear!!.W && this.W != rightGear!!.E && !rightGear!!.hasRotated) {
                rightGear!!.rotateCounterClock()
            } else if (this.E != leftGear!!.W && this.W == rightGear!!.E && !leftGear!!.hasRotated) {
                leftGear!!.rotateCounterClock()
            } else if (this.E != leftGear!!.W && this.W != rightGear!!.E) {
                if (!leftGear!!.hasRotated) {
                    leftGear!!.rotateCounterClock()
                }
                if (!rightGear!!.hasRotated) {
                    rightGear!!.rotateCounterClock()
                }
            }
        } else if (rightGear == null && leftGear != null) {
            if (this.E != leftGear!!.W && !leftGear!!.hasRotated) {
                leftGear!!.rotateCounterClock()
            }
        } else if (rightGear != null && leftGear == null) {
            if (this.W != rightGear!!.E && !rightGear!!.hasRotated) {
                rightGear!!.rotateCounterClock()
            }
        }
        val temp = NE
        NE = E
        E = SE
        SE = S
        S = SW
        SW = W
        W = NW
        NW = N
        N = temp
    }


    fun rotateCounterClock() {
        hasRotated = true
        if (leftGear != null && rightGear != null) {
            if (this.E == leftGear!!.W && this.W != rightGear!!.E && !rightGear!!.hasRotated) {
                rightGear!!.rotateClock()
            } else if (this.E != leftGear!!.W && this.W == rightGear!!.E && !leftGear!!.hasRotated) {
                leftGear!!.rotateClock()
            } else if (this.E != leftGear!!.W && this.W != rightGear!!.E) {
                if (!leftGear!!.hasRotated) {
                    leftGear!!.rotateClock()
                }
                if (!rightGear!!.hasRotated) {
                    rightGear!!.rotateClock()
                }
            }
        } else if (rightGear == null && leftGear != null) {
            if (this.E != leftGear!!.W && !leftGear!!.hasRotated) {
                leftGear!!.rotateClock()
            }
        } else if (rightGear != null && leftGear == null) {
            if (this.W != rightGear!!.E && !rightGear!!.hasRotated) {
                rightGear!!.rotateClock()
            }
        }
        val temp = N
        N = NW
        NW = W
        W = SW
        SW = S
        S = SE
        SE = E
        E = NE
        NE = temp
    }
}