package money

interface Expression {
    fun plus(addend: Expression): Expression
    fun reduce(bank: Bank, to: String): Money
}
