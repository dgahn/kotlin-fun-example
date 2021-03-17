package mg.dgahn.combinator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

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

    test("drop은 앞의 값이 n개 제외된 리스트를 반환한다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        list.drop(0) shouldBe list
        list.drop(1) shouldBe FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil))))
        list.drop(2) shouldBe FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))
        list.drop(3) shouldBe FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil))
        list.drop(4) shouldBe FunList.Cons(5.0, FunList.Nil)
        list.drop(5) shouldBe FunList.Nil
    }

    test("dropWhile은 p가 true가 되면 반환한다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        list.dropWhile { it == 11.0 } shouldBe list
        list.dropWhile { it == 2.0 } shouldBe FunList.Cons(
            2.0,
            FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))
        )
        list.dropWhile { it == 33.0 } shouldBe FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))
        list.dropWhile { it == 4.0 } shouldBe FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil))
        list.dropWhile { it == 5.0 } shouldBe FunList.Cons(5.0, FunList.Nil)
        list.dropWhile { it == 6.0 } shouldBe FunList.Nil
    }

    test("take는 앞에서부터 n개를 가져온다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        list.take(0) shouldBe FunList.Nil
        list.take(1) shouldBe FunList.Cons(11.0, FunList.Nil)
        list.take(2) shouldBe FunList.Cons(11.0, FunList.Cons(2.0, FunList.Nil))
        list.take(3) shouldBe FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Nil)))
        list.take(4) shouldBe FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Nil))))
        list.take(5) shouldBe FunList.Cons(
            11.0,
            FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil))))
        )
    }

    test("takeWhile는 앞에서부터 n개를 가져온다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        list.takeWhile { it >= 34.0 } shouldBe FunList.Nil
        list.takeWhile { it >= 33.0 } shouldBe FunList.Cons(33.0, FunList.Nil)
        list.takeWhile { it >= 11.0 } shouldBe FunList.Cons(11.0, FunList.Cons(33.0, FunList.Nil))
        list.takeWhile { it >= 5.0 } shouldBe FunList.Cons(11.0, FunList.Cons(33.0, FunList.Cons(5.0, FunList.Nil)))
    }

    test("map은 원소의 값을 변경한 결과를 반환한다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        list.map { it + 1.0 } shouldBe FunList.Cons(12.0, FunList.Cons(3.0, FunList.Cons(34.0, FunList.Cons(5.0, FunList.Cons(6.0, FunList.Nil)))))
        list.map { it * 2 } shouldBe FunList.Cons(22.0, FunList.Cons(4.0, FunList.Cons(66.0, FunList.Cons(8.0, FunList.Cons(10.0, FunList.Nil)))))
    }

    test("funListOf로 funList을 만들 수 있다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        funListOf(11.0, 2.0, 33.0, 4.0, 5.0) shouldBe list
    }

    test("indexedMap은 index와 함께 값을 변경할 수 있다.") {
        val list: FunList<Double> =
            FunList.Cons(11.0, FunList.Cons(2.0, FunList.Cons(33.0, FunList.Cons(4.0, FunList.Cons(5.0, FunList.Nil)))))

        list.indexedMap { i, d -> i + d } shouldBe funListOf(11.0, 3.0, 35.0, 7.0, 9.0)
    }

})