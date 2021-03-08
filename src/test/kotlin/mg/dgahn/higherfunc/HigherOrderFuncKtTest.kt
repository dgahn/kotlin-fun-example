package mg.dgahn.higherfunc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HigherOrderFuncKtTest : FunSpec({

    test("고차함수를 통해 계산 로직을 수행할 수 있다.") {
        val sum: (Int, Int) -> Int = { x, y -> x + y }
        val minus: (Int, Int) -> Int = { x, y -> x - y }
        val product: (Int, Int) -> Int = { x, y -> x * y }
        val twiceSum: (Int, Int) -> Int = { x, y -> (x + y) * 2 }

        higherOrder(sum, 1, 5) shouldBe 6
        higherOrder(minus, 5, 2) shouldBe 3
        higherOrder(product, 4, 2) shouldBe 8
        higherOrder(twiceSum, 8, 2) shouldBe 20
    }

})