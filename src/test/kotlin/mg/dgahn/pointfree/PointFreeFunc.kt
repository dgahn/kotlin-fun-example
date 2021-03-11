package mg.dgahn.pointfree

import mg.dgahn.currying.curried
import kotlin.math.abs

// 함수 합성을 사용해서 매개변수나 타입 선언 없이 함수를 만드는 방식

val absolute = { i: List<Int> -> i.map { abs(it) } }
val negative = { i: List<Int> -> i.map { -it } }
val minimum = { i: List<Int> -> i.minOrNull() }
val maxBy = { i: List<Int> -> i.maxOrNull()!! }
val power = { i: Int -> i * i }
val powerOfTwo = { x: Int -> power(x.toDouble(), 2).toInt() }
val curriedGcd = { m: Int, n: Int -> gcd(m, powerOfTwo(n)) }.curried()
val composedGcdPowerOfTwo = curriedGcd compose powerOfTwo

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput)) }
}

tailrec fun gcd(m: Int, n: Int): Int = when (n) {
    0 -> m
    else -> gcd(n, m % n)
}

tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when (n) {
    0 -> acc
    else -> power(x, n - 1, x * acc)
}

tailrec fun <P1, P2, R> zipWith(
    func: (P1, P2) -> R,
    list1: List<P1>,
    list2: List<P2>,
    acc: List<R> = listOf()
): List<R> = when {
    list1.isEmpty() || list2.isEmpty() -> acc
    else -> {
        val zipList = acc + listOf(func(list1.first(), list2.first()))
        zipWith(func, list1.drop(1), list2.drop(1), zipList)
    }
}