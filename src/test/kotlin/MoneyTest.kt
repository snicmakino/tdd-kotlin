import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import money.Franc
import money.Money

class MoneyTest : FunSpec({
    test("掛け算をテストする") {
        val five = Money.dollar(5)
        five.times(2) shouldBe Money.dollar(10)
        five.times(3) shouldBe Money.dollar(15)
    }

    test("等価性をテストする") {
        Money.dollar(5) shouldBe Money.dollar(5)
        Money.dollar(5) shouldNotBe Money.dollar(6)
        Franc(5) shouldBe Franc(5)
        Franc(5) shouldNotBe Franc(6)
        Money.dollar(5) shouldNotBe Franc(5)
    }

    test("フランの掛け算をテストする") {
        val five = Franc(5)
        five.times(2) shouldBe Franc(10)
        five.times(3) shouldBe Franc(15)
    }
})
