fun main(args: Array<String>) {
    TestCaseTest("testRunning").run()
    TestCaseTest("testSetUp").run()
}

open class TestCase(private val name: String) {
    open fun setUp() {}
    fun run() {
        setUp()
        val jClass = this.javaClass
        val method = jClass.getMethod(name)
        method.invoke(this)
    }
}

class WasRun(name: String) : TestCase(name) {
    var wasSetUp = false
    var wasRun = false

    override fun setUp() {
        wasRun = false
        wasSetUp = true
    }

    fun testMethod() {
        wasRun = true
    }
}

class TestCaseTest(name: String) : TestCase(name) {
    lateinit var test: WasRun

    override fun setUp() {
        test = WasRun("testMethod")
    }

    fun testRunning() {
        test.run()
        check(test.wasRun)
    }

    fun testSetUp() {
        test.run()
        check(test.wasSetUp)
    }
}
