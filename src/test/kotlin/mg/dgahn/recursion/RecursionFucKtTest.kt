package mg.dgahn.recursion

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RecursionFucKtTest : FunSpec({

    test("피보나치 수열에 대한 테스트") {
        fibo(0) shouldBe 1
        fibo(1) shouldBe 1
        fibo(2) shouldBe 2
        fibo(3) shouldBe 3
        fibo(4) shouldBe 5
        fibo(5) shouldBe 8
    }

    test("X의 n승을 구하는 함수 테스트") {
        power(2.0, 0) shouldBe 1.0
        power(2.0, 1) shouldBe 2.0
        power(2.0, 2) shouldBe 4.0
        power(2.0, 3) shouldBe 8.0
        power(2.0, 4) shouldBe 16.0
        power(2.0, 5) shouldBe 32.0
    }

    test("n 팩터리얼 n!을 구하는 함수 테스트") {
        factorial(1) shouldBe 1
        factorial(2) shouldBe 2
        factorial(3) shouldBe 6
        factorial(4) shouldBe 24
        factorial(5) shouldBe 120
    }

    test("reverse 함수는 문자열을 뒤집을 수 있다.") {
        reverse("12345") shouldBe "54321"
        reverse("abcd") shouldBe "dcba"
    }

    test("toBinary 함수는 이진수로 바꿔준다.") {
        toBinary(0) shouldBe "0"
        toBinary(1) shouldBe "1"
        toBinary(2) shouldBe "10"
        toBinary(3) shouldBe "11"
        toBinary(4) shouldBe "100"
        toBinary(5) shouldBe "101"
        toBinary(6) shouldBe "110"
        toBinary(7) shouldBe "111"
        toBinary(8) shouldBe "1000"
    }

    test("replicate 함수는 element를 n번 반복된 배열을 반환한다.") {
        replicate(0, 5) shouldBe listOf()
        replicate(1, 5) shouldBe listOf(5)
        replicate(2, 5) shouldBe listOf(5, 5)
        replicate(3, 5) shouldBe listOf(5, 5, 5)
        replicate(4, 5) shouldBe listOf(5, 5, 5, 5)
        replicate(5, 5) shouldBe listOf(5, 5, 5, 5, 5)
    }

    test("take 함수는 리스트에서 값을 원하는 갯수만큼 가져온다.") {
        take(3, listOf(1, 2, 3, 4, 5, 6)) shouldBe listOf(1, 2, 3)
        take(2, listOf(10, 9, 8, 5)) shouldBe listOf(10, 9)
    }

    test("elem 함수는 list에서 num이 있는지 확인할 수 있다.") {
        elem(5, listOf(1, 2, 3, 4)) shouldBe false
        elem(5, listOf(1, 2, 3, 4, 5)) shouldBe true
    }

    test("repeat 함수는 n번 만큼 반복한다.") {
        takeSequence(5, repeat(3)) shouldBe listOf(3, 3, 3, 3, 3)
    }

    test("zip 함수는 두 list의 짝을 맞춘 배열을 반환한다.") {
        zip(listOf(1, 2, 3), listOf(4, 5, 6)) shouldBe listOf(Pair(1, 4), Pair(2, 5), Pair(3, 6))
        zip(listOf(1), listOf(4, 5, 6)) shouldBe listOf(Pair(1, 4))
        zip(listOf(1, 5), listOf(4, 5, 6)) shouldBe listOf(Pair(1, 4), Pair(5, 5))
    }

    test("quicksort를 이용해서 정렬을 할 수 있다.") {
        quicksort(listOf(4, 3, 5, 7, 1)) shouldBe listOf(1, 3, 4, 5, 7)
        quicksort(listOf(9, 0, 4, 3, 1, 8)) shouldBe listOf(0, 1, 3, 4, 8, 9)
    }

    test("gcd 함수를 이용해서 최대공약수를 구할 수 있다.") {
        gcd(10, 5) shouldBe 5
        gcd(5, 10) shouldBe 5
        gcd(10, 11) shouldBe 1
        gcd(6, 8) shouldBe 2
    }

})