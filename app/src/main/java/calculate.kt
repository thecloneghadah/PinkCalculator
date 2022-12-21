class calculate {
    var currNum = 0
    var prevNum = 0
    var prevEq = "="
    var result = 0
    fun calulate(eq:String){
        when(prevEq){
            "+" -> {
                result = currNum + prevNum
            }
            "-" -> {
                result = currNum - prevNum
            }
            "/" -> {
                result = currNum / prevNum
            }
            "*" -> {
                result = currNum * prevNum
            }
        }
        prevNum = currNum
        currNum = result
        prevEq = eq
    }
}