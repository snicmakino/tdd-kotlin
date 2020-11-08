import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import money.Bank
import money.Expression
import money.Money
import money.Sum

class MoneyTest : FunSpec({
    test("掛け算をテストする") {
        val five: Money = Money.dollar(5)
        five.times(2) shouldBe Money.dollar(10)
        five.times(3) shouldBe Money.dollar(15)
    }

    test("等価性をテストする") {
        Money.dollar(5) shouldBe Money.dollar(5)
        Money.dollar(5) shouldNotBe Money.dollar(6)
        Money.dollar(5) shouldNotBe Money.franc(5)
    }

    test("フランの掛け算をテストする") {
        val five: Money = Money.franc(5)
        five.times(2) shouldBe Money.franc(10)
        five.times(3) shouldBe Money.franc(15)
    }

    test("通貨のテスト") {
        Money.dollar(1).currency() shouldBe "USD"
        Money.franc(1).currency() shouldBe "CHF"
    }

    test("単純な足し算のテスト") {
        val five: Money = Money.dollar(5)
        val sum: Expression = five.plus(five)
        val bank = Bank()
        val reduced: Money = bank.reduce(sum, "USD") as Money
        reduced shouldBe Money.dollar(10)
    }

    test("足し算の結果のSumをテスト") {
        val five: Money = Money.dollar(5)
        val result: Expression = five.plus(five)
        val sum: Sum = result as Sum
        sum.augend shouldBe five
        sum.addend shouldBe five
    }

    test("Sumの換算結果をテスト") {
        val sum: Expression = Sum(Money.dollar(3), Money.dollar(4))
        val bank = Bank()
        val result: Money = bank.reduce(sum, "USD") as Money
        result shouldBe Money.dollar(7)
    }

    test("Moneyの換算結果をテスト") {
        val bank = Bank()
        val result: Money = bank.reduce(Money.dollar(1), "USD") as Money
        result shouldBe Money.dollar(1)
    }

    test("異なる通貨の換算のテスト") {
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result: Money = bank.reduce(Money.franc(2), "USD")
        result shouldBe Money.dollar(1)
    }

    test("同一のレートのテスト") {
        Bank().rate("USD", "USD") shouldBe 1
    }
})
