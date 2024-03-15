/**
 * Interfaz que define las operaciones de combate para un personaje o monstruos en el juego.
 */
interface Combate {
    fun atacar(): Int
    fun recibirAtaque(danio: Int)
    fun estaVivo(): Boolean
    fun mostrarStats()
}
