package money

open class Money(protected val amount: Int, protected val currency: String) {

    override fun equals(other: Any?): Boolean {
        if (other !is Money) {
            return false
        }
        return amount == other.amount
                && currency() == other.currency()
    }

    open fun times(multiplier: Int): Money {
        return Money(amount * multiplier, currency)
    }

    fun currency(): String {
        return currency
    }

    companion object {
        fun dollar(amount: Int): Dollar {
            return Dollar(amount, "USD")
        }

        fun franc(amount: Int): Franc {
            return Franc(amount, "CHF")
        }
    }

    override fun toString(): String {
        return "$amount $currency"
    }
}
