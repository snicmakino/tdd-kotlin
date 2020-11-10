import java.lang.reflect.Method

fun main(args: Array<String>) {
    val suite = TestSuite()
    suite.add(TestCaseTest("testTemplateMethod"))
    suite.add(TestCaseTest("testResult"))
    suite.add(TestCaseTest("testFailedResult"))
    suite.add(TestCaseTest("testFailedResultFormatting"))
    suite.add(TestCaseTest("testSuite"))
    val result = TestResult()
    suite.run(result)
    println(result.summary())
}

open class TestCase(private val name: String) {

    open fun setUp() {}
    open fun tearDown() {}

    fun run(result: TestResult) {
        result.testStarted()
        setUp()
        try {
            val method = method()
            method.invoke(this)
        } catch (e: Exception) {
            result.testFailed()
        }
        tearDown()
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

class TestSuite {
    val tests = mutableListOf<TestCase>()

    fun add(test: TestCase) {
        tests.add(test)
    }

    fun run(result: TestResult) {
        tests.map { it.run(result) }
    }
}

class TestCaseTest(name: String) : TestCase(name) {
    lateinit var result: TestResult

    override fun setUp() {
        result = TestResult()
    }

    fun testTemplateMethod() {
        val test = WasRun("testMethod")
        test.run(result)
        check("setUp testMethod tearDown " == test.log)
    }

    fun testResult() {
        val test = WasRun("testMethod")
        test.run(result)
        check("1 run, 0 failed" == result.summary())
    }

    fun testFailedResult() {
        val test = WasRun("testBrokenMethod")
        test.run(result)
        check("1 run, 1 failed" == result.summary())
    }

    fun testFailedResultFormatting() {
        result.testStarted()
        result.testFailed()
        check("1 run, 1 failed" == result.summary())
    }

    fun testSuite() {
        val suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        suite.run(result)
        check("2 run, 1 failed" == result.summary())
    }
}
