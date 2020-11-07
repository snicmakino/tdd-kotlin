import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import money.Dollar
import money.Franc

class MoneyTest : FunSpec({
    test("掛け算をテストする") {
        val five = Dollar(5)
        five.times(2) shouldBe Dollar(10)
        five.times(3) shouldBe Dollar(15)
    }

    test("等価性をテストする") {
        Dollar(5) shouldBe Dollar(5)
        Dollar(5) shouldNotBe Dollar(6)
    }

    test("フランの掛け算をテストする") {
        val five = Franc(5)
        five.times(2) shouldBe Franc(10)
        five.times(3) shouldBe Franc(15)
    }
})
