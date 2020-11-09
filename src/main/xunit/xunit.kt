fun main(args: Array<String>) {
    val test = WasRun("testMethod")
    println(test.wasRun)
    test.testMethod()
    println(test.wasRun)
}

class WasRun(val name: String) {
    var wasRun: Boolean = false

    fun testMethod() {
        wasRun = true
    }
}
