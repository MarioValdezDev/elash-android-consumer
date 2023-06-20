package mx.mariovaldez.elashstudioapp.ktx


fun String.AddMoneyFormat(): String = String.format("$%.2f", this.toDouble())
