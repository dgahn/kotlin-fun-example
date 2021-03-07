package mg.dgahn.memoization

var memo = Array(100) { -1 }

fun fiboMemoization(n: Int): Int = when {
    n == 0 -> 0
    n == 1 -> 1
    memo[n] != -1 -> memo[n]
    else -> {
        println("fiboMemoization($n)")
        memo[n] = fiboMemoization(n - 2) + fiboMemoization(n - 1)
        memo[n]
    }
}

tailrec fun fiboFp(n: Int, first: Int = 0, second: Int = 1): Int = when (n) {
    0 -> first
    1 -> second
    else -> fiboFp(n - 1, second, first + second)
}

tailrec fun factorialFp(n: Int, result: Int = 1): Int = when(n) {
    0 -> 0
    1 -> result
    else -> factorialFp(n - 1, result * n)
}