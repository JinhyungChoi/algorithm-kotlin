// 가장 가까운 공통 조상

fun main(){
    val br = System.`in`.bufferedReader()
    val T = br.readLine().toInt()

    fun findParent(child: Int, parent: Array<Int>, parentList: ArrayList<Int>){
        if (parent[child] == child)
            return
        parentList.add(parent[child])
        findParent(parent[child], parent, parentList)
    }
    repeat(T){
        val N = br.readLine().toInt()
        val parent = Array(N+1){0}
        for (i in parent.indices){
            parent[i] = i
        }
        repeat(N-1){
            val (p,c) = br.readLine().split(' ').map { it.toInt() }
            parent[c] = p
        }
        val (n1, n2) = br.readLine().split(' ').map { it.toInt() }
        val parentN1 = arrayListOf<Int>()
        val parentN2 = arrayListOf<Int>()
        parentN1.add(n1)
        parentN2.add(n2)
        findParent(n1, parent, parentN1)
        findParent(n2, parent, parentN2)
        val pn1Set = parentN1.toSet()
        val pn2Set = parentN2.toSet()
        println(pn1Set.intersect(pn2Set).toList()[0])
    }
}