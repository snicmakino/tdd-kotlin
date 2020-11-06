import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MoneyTest : FunSpec({
    test("掛け算をテストする") {
        val five = Dollar(5)
        five.times(2)
        five.amount shouldBe 10
    }
})
