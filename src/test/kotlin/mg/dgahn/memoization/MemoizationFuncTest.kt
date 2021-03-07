package mg.dgahn.memoization

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MemoizationFuncTest : FunSpec({

    test("fiboMemoization 함수를 통해서 피보나치 수열을 구할 수 있다.") {
        fiboMemoization(0) shouldBe 0
        fiboMemoization(1) shouldBe 1
        fiboMemoization(2) shouldBe 1
        fiboMemoization(3) shouldBe 2
        fiboMemoization(4) shouldBe 3
        fiboMemoization(5) shouldBe 5
    }

    test("fiboFp 함수를 통해서 피보나치 수열을 구할 수 있다.") {
        fiboFp(0) shouldBe 0
        fiboFp(1) shouldBe 1
        fiboFp(2) shouldBe 1
        fiboFp(3) shouldBe 2
        fiboFp(4) shouldBe 3
        fiboFp(5) shouldBe 5
    }

    test("factorailFp 함수를 통해서 factorial 연산을 할 수 있다.") {
        factorialFp(0) shouldBe 0
        factorialFp(1) shouldBe 1
        factorialFp(2) shouldBe 2
        factorialFp(3) shouldBe 6
        factorialFp(4) shouldBe 24
        factorialFp(5) shouldBe 120
    }

})