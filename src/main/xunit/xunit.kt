fun main(args: Array<String>) {
    TestCaseTest("testRunning").run()
}

open class TestCase(private val name: String) {
    fun run() {
        val jClass = this.javaClass
        val method = jClass.getMethod(name)
        method.invoke(this)
    }
}

class WasRun(name: String) : TestCase(name) {
    var wasRun: Boolean = false
    fun testMethod() {
        wasRun = true
    }
}

class TestCaseTest(name: String) : TestCase(name) {
    fun testRunning() {
        val test = WasRun("testMethod")
        check(!test.wasRun)
        test.run()
        check(test.wasRun)
    }
}
