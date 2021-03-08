package mg.dgahn.particial

/*
부분 함수는 특정 조건에 따라 함수를 실행하기 위한 방법이다.
 */

// 입력 값에 대해서 확인하는 인자인 condition
// 조건에 맞을 때 수행하는 함수 f
class PartialFunction<P, R>(
    private val condition: (P) -> Boolean,
    private val f: (P) -> R
) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported.")
    }

    fun invokeOrElse(p: P, default: R): R = when {
        condition(p) -> f(p)
        else -> default
    }

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
        PartialFunction(
            { this.isDefinedAt(it) || that.isDefinedAt(it) },
            {
                when {
                    this.isDefinedAt(it) -> this(it)
                    that.isDefinedAt(it) -> that(it)
                    else -> throw IllegalArgumentException("$it isn't defined")
                }
            }
        )

    fun isDefinedAt(p: P): Boolean = condition(p)
}

val condition: (Int) -> Boolean = { it in 1..3 }
val body: (Int) -> String = {
    when (it) {
        1 -> "one"
        2 -> "two"
        3 -> "Three"
        else -> throw IllegalArgumentException()
    }
}

val isEven = PartialFunction<Int, String>({ it % 2 == 0 }, { "$it is even" })

fun <P, R> ((P) -> (R)).toPartialFunction(definedAt: (P) -> Boolean): PartialFunction<P, R> =
    PartialFunction(definedAt, this)

