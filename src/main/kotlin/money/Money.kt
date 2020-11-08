package money

class Money(private val amount: Int, private val currency: String) : Expression {

    fun times(multiplier: Int): Money {
        return Money(amount * multiplier, currency)
    }

    fun plus(addend: Money): Expression {
        return Money(amount + addend.amount, currency)
    }

    fun currency(): String {
        return currency
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Money) {
            return false
        }
        return amount == other.amount
                && currency() == other.currency()
    }

    override fun toString(): String {
        return "$amount $currency"
    }

    companion object {
        fun dollar(amount: Int): Money {
            return Money(amount, "USD")
        }

        fun franc(amount: Int): Money {
            return Money(amount, "CHF")
        }
    }
}
