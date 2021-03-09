package mg.dgahn.particial

/*
부분 함수는 특정 조건에 따라 함수가 실패할 때 예외처리를 하기 위한 방법이다. 가장 좋은 것은 부분 함수를 사용하지 않게 되는 것이 좋다.
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

/*
 * 부분 적용 함수는 매개변수의 일부만 전달 할 수 있고 아에 전달하지 않을 수도 있다.
 */

fun <P1, P2, R> ((P1, P2) -> R).partial1(p1: P1): (P2) -> R {
    return { p2 -> this(p1, p2) }
}

fun <P1, P2, R> ((P1, P2) -> R).partial2(p2: P2): (P1) -> R {
    return { p1 -> this(p1, p2) }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p1: P1): (P2, P3) -> R {
    return { p2, p3 -> this(p1, p2, p3) }
}


