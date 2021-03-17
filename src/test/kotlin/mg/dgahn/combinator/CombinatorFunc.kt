package mg.dgahn.combinator

/*
 * 함수형 프로그래밍에서 사용하는 컬렉션들에 부수 효과가 없는 고차 함수를 사용하는데 이 함수들을 콤비네이터라고 한다.
 */

sealed class FunList<out T> {
    object Nil : FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.reverse(acc.addHead(head))
}

tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> FunList.Cons(value, acc).reverse()
    is FunList.Cons -> tail.appendTail(value, acc)
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> tail
}

fun <T> FunList<T>.getHead(): T = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> head
}

tailrec fun <T> FunList<T>.filter(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> if (p(head)) {
        tail.filter(acc.addHead(head), p)
    } else {
        tail.filter(acc, p)
    }
}

tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when (n) {
    0 -> this
    else -> getTail().drop(n - 1)
}

tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> this
    is FunList.Cons -> if (p(head)) {
        this
    } else {
        getTail().dropWhile(p)
    }
}

tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = FunList.Nil): FunList<T> = when (n) {
    0 -> acc.reverse()
    else -> when (this) {
        FunList.Nil -> take(0, acc)
        is FunList.Cons -> getTail().take(n - 1, acc.addHead(head))
    }
}

tailrec fun <T> FunList<T>.takeWhile(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> if (!p(head)) {
        tail.takeWhile(acc, p)
    } else {
        tail.takeWhile(acc.addHead(head), p)
    }
}

tailrec fun <T, R> FunList<T>.map(acc: FunList<R> = FunList.Nil, f: (T) -> R): FunList<R> = when(this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.map(acc.addHead(f(head)), f)
}

fun <T> funListOf(vararg elements: T): FunList<T> = elements.toFunList()

private fun <T> Array<out T>.toFunList(): FunList<T> = when {
    this.isEmpty() -> FunList.Nil
    else -> FunList.Cons(this[0], this.copyOfRange(1, this.size).toFunList())
}

tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = FunList.Nil, f: (Int, T) -> R): FunList<R> =
    when (this) {
        FunList.Nil -> acc.reverse()
        is FunList.Cons -> tail.indexedMap(index + 1, acc.addHead(f(index, head)), f)
    }
