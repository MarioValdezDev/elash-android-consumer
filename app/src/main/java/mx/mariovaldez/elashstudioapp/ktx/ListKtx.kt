package mx.mariovaldez.elashstudioapp.ktx

internal fun List<Float>.toPercent(): List<Float> {
    return this.map { item ->
        item * 100 / this.sum()
    }
}