import java.lang.reflect.Method

fun main(args: Array<String>) {
    TestCaseTest("testTemplateMethod").run()
    TestCaseTest("testResult").run()
    TestCaseTest("testBrokenMethod").run()
    TestCaseTest("testFailedResultFormatting").run()
}

open class TestCase(private val name: String) {

    open fun setUp() {}
    open fun tearDown() {}

    fun run(): TestResult {
        val result = TestResult()
        result.testStarted()
        setUp()
        try {
            val method = method()
            method.invoke(this)
        } catch (e: Exception) {
            result.testFailed()
        }
        tearDown()
        return result
    }

    private fun method(): Method {
        val jClass = this.javaClass
        return jClass.getMethod(name)
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
    private var runCount = 0
    private var errorCount = 0

    fun testStarted() {
        runCount++
    }

    fun testFailed() {
        errorCount++
    }

    fun summary(): String {
        return "$runCount run, $errorCount failed"
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

    fun testFailedResultFormatting() {
        val result = TestResult()
        result.testStarted()
        result.testFailed()
        check("1 run, 1 failed" == result.summary())
    }
}
