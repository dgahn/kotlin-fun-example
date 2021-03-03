package mg.dgahn.recursion

fun String.head() = first()
fun String.tail() = drop(1)
fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

fun fibo(n: Int): Int = when {
    n <= 1 -> 1
    else -> fibo(n - 1) + fibo(n - 2)
}

fun power(x: Double, n: Int): Double = when (n) {
    0 -> 1.0
    1 -> x
    else -> x * power(x, n - 1)
}

fun factorial(n: Int): Int = when (n) {
    0 -> 0
    1 -> 1
    else -> n * factorial(n - 1)
}

fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}

fun toBinary(n: Int): String = when (n) {
    0 -> "0"
    1 -> "1"
    else -> toBinary(n / 2) + "${n % 2}"
}

fun replicate(n: Int, element: Int): List<Int> = when (n) {
    0 -> listOf()
    else -> replicate(n - 1, element) + listOf(element)
}

fun take(n: Int, list: List<Int>): List<Int> = when (n) {
    0 -> listOf()
    else -> listOf(list.head()) + take(n - 1, list.tail())
}

fun elem(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    else -> if (list.head() == num) true else elem(num, list.tail())
}

operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
    private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
    private val otherIterator: Iterator<T> by lazy { other().iterator() }

    override fun iterator() = object : Iterator<T> {
        override fun next(): T =
            if (thisIterator.hasNext())
                thisIterator.next()
            else
                otherIterator.next()

        override fun hasNext(): Boolean = thisIterator.hasNext() || otherIterator.hasNext()
    }
}

fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }

fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> = when {
    n <= 0 -> listOf()
    sequence.none() -> listOf()
    else -> listOf(sequence.first()) + takeSequence(n - 1, sequence.drop(1))
}

fun zip(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> = when {
    list1.isEmpty() || list2.isEmpty() -> listOf()
    else -> listOf(Pair(list1.head(), list2.head())) + zip(list1.tail(), list2.tail())
}