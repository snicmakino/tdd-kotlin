package money

abstract class Money(protected val amount: Int, private val currency: String) {

    override fun equals(other: Any?): Boolean {
        if (other !is Money) {
            return false
        }
        return amount == other.amount
                && this.javaClass.kotlin == other.javaClass.kotlin
    }

    abstract fun times(multiplier: Int): Money

    fun currency(): String {
        return currency
    }

    companion object {
        fun dollar(amount: Int): Money {
            return Dollar(amount, "USD")
        }

        fun franc(amount: Int): Money {
            return Franc(amount, "CHF")
        }
    }
}
