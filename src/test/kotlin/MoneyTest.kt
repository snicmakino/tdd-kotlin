import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MoneyTest : FunSpec({
    test("掛け算をテストする") {
        val five: Dollar = Dollar(5)
        five.times(2) shouldBe Dollar(10)
        five.times(3) shouldBe Dollar(15)
    }

    test("等価性をテストする") {
        Dollar(5) shouldBe Dollar(5)
        Dollar(5) shouldNotBe Dollar(6)
    }
})
