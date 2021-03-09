package mg.dgahn.particial

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PartialFuncKtTest : FunSpec({

    test("고차함수를 통해 계산 로직을 수행할 수 있다.") {
        val oneTwoThree = PartialFunction(condition, body)

        oneTwoThree.isDefinedAt(3) shouldBe true
        oneTwoThree.isDefinedAt(4) shouldBe false

        oneTwoThree(3) shouldBe "Three"
    }

    test("isEven을 통해서 조건을 확인해서 부분 함수를 수행할 수 있다.") {
        isEven(100) shouldBe "100 is even"
    }

    test("확장함수를 통해서 손쉽게 부분함수를 사용할 수 있다.") {
        val condition: (Int) -> Boolean = { it.rem(2) == 0 }
        val body: (Int) -> String = { "$it is even" }

        val isEven = body.toPartialFunction(condition)

        isEven(100) shouldBe "100 is even"
    }

    test("invokeOrElse 함수는 입력 값 p가 조건에 맞지 않을때 기본값 default를 반환한다.") {
        val condition: (Int) -> Boolean = { it.rem(2) == 0 }
        val body: (Int) -> String = { "$it is even" }

        val isEven = body.toPartialFunction(condition)

        isEven.invokeOrElse(1, "odd") shouldBe "odd"
    }

    test("orElse 함수는 PartialFunction의 입력값 p가 조건에 맞으면 PartialFunction을 그대로(this) 반환하고, 조건에 맞지 않으면 that을 반환한다.") {
        val condition1: (Int) -> Boolean = { it.rem(2) == 0 }
        val condition2: (Int) -> Boolean = { it.rem(2) == 1 }
        val body1: (Int) -> String = { "$it is even" }
        val body2: (Int) -> String = { "$it is odd" }

        val isEven = body1.toPartialFunction(condition1)
        val isOdd = body2.toPartialFunction(condition2)

//        isEven.orElse(isOdd)(1) shouldBe "1 is odd"
        isOdd(1) shouldBe "1 is odd"
        isEven.orElse(isOdd)(1) shouldBe "1 is odd"
    }

    test("부분 적용 함수는 함수의 일부 매개변수만 받아서 적용할 수 있다.") {
        val expected = "HelloWorld"
        val func = { a: String, b: String -> a + b }

        val partiallyAppliedFunc1 = func.partial1("Hello")
        val result1 = partiallyAppliedFunc1("World")

        result1 shouldBe expected

        val partiallyAppliedFunc2 = func.partial2("World")
        val result2 = partiallyAppliedFunc2("Hello")

        result2 shouldBe expected

        val func1 = { a: String, b: String, c: String -> a + b + c }

        val partiallyAppliedFunc3 = func1.partial3("Hello")
        val result3 = partiallyAppliedFunc3.partial1(" ")("World")

        result3 shouldBe "Hello World"
    }

})