package mg.dgahn.pointfree

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import mg.dgahn.recursion.replicate
import kotlin.math.max

class PointFreeFuncKtTest : FunSpec({

    test("합성 함수로 만든 함수로 최소값을 구할 수 있다.") {
        val composed = minimum compose negative compose absolute
        val result = composed(listOf(3, -1, 5, -2, -4, 8, 14))

        result shouldBe -14
    }

    test("최댓값의 제곱을 구하는 함수를 합성할 수 있다.") {
        val composed = power compose maxBy
        val result = composed(listOf(3, -1, 5, -2, -4, 8, 14))

        result shouldBe 196
    }

    test("두 수의 제곱의 최대 공약수를 구하는 함수를 합성함수로 구할 수 있다.") {
        composedGcdPowerOfTwo(25)(5) shouldBe 25
    }

    test("zipWith 함수를 통해서 연산한 결과를 zip 할 수 있다.") {
        val list1 = listOf(6, 3, 2, 1, 4)
        val list2 = listOf(7, 4, 2, 6, 3)

        val add = { p1: Int, p2: Int -> p1 + p2 }
        val result1 = zipWith(add, list1, list2)
        result1 shouldBe listOf(13, 7, 4, 7, 7)

        val max = { p1: Int, p2: Int -> max(p1, p2) }
        val result2 = zipWith(max, list1, list2)
        result2 shouldBe listOf(7, 4, 2, 6, 4)

        val strcat = { p1: String, p2: String -> p1 + p2 }
        val result3 = zipWith(strcat, listOf("a", "b"), listOf("c", "d"))
        result3 shouldBe listOf("ac", "bd")

        val product = { p1: Int, p2: Int -> p1 * p2 }
        val result4 = zipWith(product, replicate(3, 5), (1..5).toList())
        result4 shouldBe listOf(5, 10, 15)
    }

})