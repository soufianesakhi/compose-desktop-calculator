package jetbrains.compose.calculator.service

import org.mariuszgromada.math.mxparser.Expression
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

val OPERATORS = arrayOf('+', '-', '*', '/')

fun calculate(input: String): String? {
    val expression = input.replace('x', '*').replace('÷', '/')
    if (!OPERATORS.any { expression.contains(it) } || OPERATORS.any { expression.endsWith(it) }) {
        return null
    }
    return try {
        val result = BigDecimal(Expression(expression).calculate())
            .round(MathContext(10, RoundingMode.UP))
        result.toString()
    } catch (e: Exception) {
        null
    }
}
