package mg.dgahn.combinator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave

class CombinatorFuncTest : FunSpec({

    test("FunList을 활용할 수 있다.") {
        val list: FunList<Int> = FunList.Cons(1, FunList.Cons(2, FunList.Nil))
    }

    test("FunList를 통해서 [1, 2, 3, 4, 5]를 가지는 intList를 생성할 수 있다.") {
        val list: FunList<Int> =
            FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Cons(5, FunList.Nil)))))
    }

    test("FunList를 통해서 [1.0, 2.0, 3.0, 4.0, 5.0]를 가지는 intList를 생성할 수 있다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))
    }

    test("FunList을 Filtering 할수 있다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        list.filter { it > 10.0 } shouldBe FunList.Cons(11.0, FunList.Cons(33.0, FunList.Nil))
    }

})