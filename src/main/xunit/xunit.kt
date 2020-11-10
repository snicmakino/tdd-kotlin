fun main(args: Array<String>) {
    TestCaseTest("testTemplateMethod").run()
    TestCaseTest("testResult").run()
//    TestCaseTest("testBrokenMethod").run()
}

open class TestCase(private val name: String) {

    open fun setUp() {}
    open fun tearDown() {}

    fun run(): TestResult {
        val result = TestResult()
        result.testStarted()
        setUp()
        val jClass = this.javaClass
        val method = jClass.getMethod(name)
        method.invoke(this)
        tearDown()
        return result
    }
}

class WasRun(name: String) : TestCase(name) {
    lateinit var log: String

    override fun setUp() {
        log = "setUp "
    }

    fun testMethod() {
        log += "testMethod "
    }

    fun testBrokenMethod() {
        throw Exception()
    }

    override fun tearDown() {
        log += "tearDown "
    }
}

class TestResult {
    var runCount = 0

    fun testStarted() {
        runCount++
    }

    fun summary(): String {
        return "$runCount run, 0 failed"
    }
}

class TestCaseTest(name: String) : TestCase(name) {
    fun testTemplateMethod() {
        val test = WasRun("testMethod")
        test.run()
        check("setUp testMethod tearDown " == test.log)
    }

    fun testResult() {
        val test = WasRun("testMethod")
        val result: TestResult = test.run()
        check("1 run, 0 failed" == result.summary())
    }

    fun testFailedResult() {
        val test = WasRun("testBrokenMethod")
        val result: TestResult = test.run()
        check("1 run, 1 failed" == result.summary())
    }
}
