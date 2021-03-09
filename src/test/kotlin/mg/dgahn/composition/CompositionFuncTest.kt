package mg.dgahn.composition

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CompositionFuncTest : FunSpec({

    test("composed 함수는 두배를 곱하고 + 3한 값을 반환한다.") {
        composed(3) shouldBe 9
    }

    test("compose 함수로 합성함수를 만들 수 있다.") {
        val addThree = { i: Int -> i + 3 }
        val twice = { i: Int -> i * 2 }
        val composedFunc = addThree compose twice
        composedFunc(3) shouldBe 9
    }

})