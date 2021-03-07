package mg.dgahn.tailrec

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import mg.dgahn.recursion.*

class TailRecFuncKtTest : FunSpec({

    test("X의 n승을 구하는 함수 테스트") {
        powerFp(2.0, 0) shouldBe 1.0
        power(2.0, 1) shouldBe 2.0
        power(2.0, 2) shouldBe 4.0
        power(2.0, 3) shouldBe 8.0
        power(2.0, 4) shouldBe 16.0
        power(2.0, 5) shouldBe 32.0
    }

    test("reverse 함수는 문자열을 뒤집을 수 있다.") {
        reverseFp("12345") shouldBe "54321"
        reverseFp("abcd") shouldBe "dcba"
    }

    test("replicate 함수는 element를 n번 반복된 배열을 반환한다.") {
        replicateFp(0, 5) shouldBe listOf()
        replicateFp(1, 5) shouldBe listOf(5)
        replicateFp(2, 5) shouldBe listOf(5, 5)
        replicateFp(3, 5) shouldBe listOf(5, 5, 5)
        replicateFp(4, 5) shouldBe listOf(5, 5, 5, 5)
        replicateFp(5, 5) shouldBe listOf(5, 5, 5, 5, 5)
    }

    test("toBinary 함수는 이진수로 바꿔준다.") {
        toBinaryFp(0) shouldBe "0"
        toBinaryFp(1) shouldBe "1"
        toBinaryFp(2) shouldBe "10"
        toBinaryFp(3) shouldBe "11"
        toBinaryFp(4) shouldBe "100"
        toBinaryFp(5) shouldBe "101"
        toBinaryFp(6) shouldBe "110"
        toBinaryFp(7) shouldBe "111"
        toBinaryFp(8) shouldBe "1000"
    }

    test("take 함수는 리스트에서 값을 원하는 갯수만큼 가져온다.") {
        takeFp(3, listOf(1, 2, 3, 4, 5, 6)) shouldBe listOf(1, 2, 3)
        takeFp(2, listOf(10, 9, 8, 5)) shouldBe listOf(10, 9)
    }

    test("elem 함수는 list에서 num이 있는지 확인할 수 있다.") {
        elemFp(5, listOf(1, 2, 3, 4)) shouldBe false
        elemFp(5, listOf(1, 2, 3, 4, 5)) shouldBe true
    }

})