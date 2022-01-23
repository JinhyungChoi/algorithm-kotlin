//행운의 문자열

fun main(){
    val br = System.`in`.bufferedReader()
    val str = br.readLine().toList().toMutableList()
    val alphaMap = Array(26){0}
    var ans = 0
    for (i in str){
        alphaMap[i-'a'] ++
    }
    fun backtrack(luck: String){
        if (luck.length == str.size){
            ans ++
            return
        }
        for (i in alphaMap.indices){
            if (alphaMap[i] == 0) {
                continue
            }
            if (luck == ""){
                alphaMap[i]--
                backtrack(luck+('a'+i))
                alphaMap[i]++
            } else {
                if (luck.last() != ('a'+i)){
                    alphaMap[i]--
                    backtrack(luck+('a'+i))
                    alphaMap[i]++
                }
            }
        }
    }
    backtrack("")
    println(ans)
}