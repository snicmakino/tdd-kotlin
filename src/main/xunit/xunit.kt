fun main(args: Array<String>) {
    TestCaseTest("testTemplateMethod").run()
}

open class TestCase(private val name: String) {
    open fun setUp() {}
    open fun tearDown() {}
    fun run() {
        setUp()
        val jClass = this.javaClass
        val method = jClass.getMethod(name)
        method.invoke(this)
        tearDown()
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

    override fun tearDown() {
        log += "tearDown "
    }
}

class TestCaseTest(name: String) : TestCase(name) {
    fun testTemplateMethod() {
        val test = WasRun("testMethod")
        test.run()
        check("setUp testMethod tearDown " == test.log)
    }
}
