package mg.dgahn.higherfunc

/*
고차 함수는 다음 두가지 조건 중 하나 이상을 만족하는 함수를 의미한다.
- 함수를 매개변수로 받는 함수
- 함수를 반환하는 함수
 */

fun higherOrderFunction1(func: () -> Unit): Unit {
    func()
}

fun higherOrderFunction2(): () -> Unit {
    return { println("Hello, Kotlin") }
}

fun higherOrder(func: (Int, Int) -> Int, x: Int, y: Int): Int = func(x, y)