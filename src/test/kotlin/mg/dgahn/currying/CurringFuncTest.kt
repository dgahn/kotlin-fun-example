package mg.dgahn.currying

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CurringFuncTest : FunSpec({

    test("커링 함수는 인자를 받은 중간 함수를 활용할 수 있다.") {
        val partial1 = multiThree(1)
        val partial2 = partial1(2)
        val partial3 = partial2(3)

        partial3 shouldBe 6

        multiThree(1)(2)(3) shouldBe 6
    }

    test("max 함수를 커링 함수로 사용할 수 있다.") {
        val partial1 = max(1)
        val max1 = partial1(2)

        max1 shouldBe 2

        val max2 = max(2)(1)
        max2 shouldBe 2
    }

    test("curried, uncurried 함수를 활용할 수 있다.") {
        val multiThree = { a: Int, b: Int, c: Int -> a * b * c }
        val curried = multiThree.curried()
        curried(1)(2)(3) shouldBe 6

        val uncurried = curried.uncurried()
        uncurried(1, 2, 3) shouldBe 6

        min.curried()(1)(2) shouldBe 1
    }
})