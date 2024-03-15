class Inventario<T> {
    private val items: MutableList<Item<T>> = mutableListOf()

    fun agregarItem(item: Item<T>) {
        items.add(item)
    }

    fun mostrarInventario() {
        println("Inventario:")
        items.forEachIndexed { index, item ->
            println("${index + 1}. ${item.contenido}, Descripcion: ${item.desc}")
        }
    }
}