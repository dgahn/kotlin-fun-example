package mg.dgahn.tailrec

import mg.dgahn.recursion.head
import mg.dgahn.recursion.tail

fun powerFp(x: Double, n: Int, result: Double = 1.0): Double = when (n) {
    0 -> 1.0
    1 -> x
    else -> powerFp(x, n - 1, x * result)
}

tailrec fun reverseFp(str: String, result: String = ""): String = when {
    str.isEmpty() -> result
    else -> reverseFp(str.tail(), str.head() + result)
}

tailrec fun toBinaryFp(n: Int, result: String = ""): String = when (n) {
    0 -> "0"
    1 -> "1$result"
    else -> toBinaryFp(n / 2, "${n % 2}$result")
}

tailrec fun replicateFp(n: Int, element: Int, result: List<Int> = emptyList()): List<Int> = when (n) {
    0 -> result
    else -> replicateFp(n - 1, element, result + element)
}

tailrec fun takeFp(n: Int, list: List<Int>, result: List<Int> = emptyList()): List<Int> = when (n) {
    0 -> result
    else -> takeFp(n - 1, list.tail(), result + list.head())
}

tailrec fun elemFp(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    else -> if (list.head() == num) true else elemFp(num, list.tail())
}
