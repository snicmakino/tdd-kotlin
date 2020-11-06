import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MoneyTest : FunSpec({
    test("掛け算をテストする") {
        val five: Dollar = Dollar(5)
        var product: Dollar = five.times(2)
        product.amount shouldBe 10
        product = five.times(3)
        product.amount shouldBe 15
    }
})
