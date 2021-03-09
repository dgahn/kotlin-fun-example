package mg.dgahn.composition

fun composed(i: Int) = addThree(twice(i))

fun addThree(i: Int) = i + 3

fun twice(i: Int) = i * 2

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput)) }
}