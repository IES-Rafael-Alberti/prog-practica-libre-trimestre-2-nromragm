/**
 * Interfaz que define las operaciones de combate para un personaje o monstruos en el juego.
 */
interface Combate {
    /**
     * Realiza un ataque y devuelve la cantidad de daño infligida.
     *
     * @return La cantidad de daño infligida por el ataque.
     */
    fun atacar(): Int

    /**
     * Recibe un ataque con la cantidad de daño especificada.
     *
     * @param danio La cantidad de daño infligida por el ataque.
     */
    fun recibirAtaque(danio: Int)

    /**
     * Verifica si el personaje o entidad está vivo.
     *
     * @return true si el personaje o monstruo está vivo, false de lo contrario.
     */
    fun estaVivo(): Boolean

    fun mostrarStats()
}
