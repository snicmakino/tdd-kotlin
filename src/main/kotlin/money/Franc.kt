package money

class Franc(amount: Int, currency: String) : Money(amount, currency) {
    override fun times(multiplier: Int): Money {
        return Franc(amount * multiplier, currency)
    }
}
