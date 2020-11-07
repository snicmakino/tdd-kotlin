import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MoneyTest : FunSpec({
    test("掛け算をテストする") {
        val five: Dollar = Dollar(5)
        var product: Dollar = five.times(2)
        product.amount shouldBe 10
        product = five.times(3)
        product.amount shouldBe 15
    }

    test("等価性をテストする") {
        Dollar(5) shouldBe Dollar(5)
        Dollar(5) shouldNotBe Dollar(6)
    }
})
