import Gestion.monstrarMensaje
import Gestion.pedirOpcion
import com.github.ajalt.mordant.rendering.TextColors.*

/**
 * Clase que representa una partida en el juego, donde un jugador se enfrenta a una lista de monstruos.
 *
 * @property dificultad La dificultad de la partida.
 * @property jugador El aventurero que participa en la partida.
 * @property listaMonstruos La lista de monstruos a los que se enfrentará el jugador.
 * @property accionesJugador La lista de acciones disponibles para el jugador durante la partida.
 */
class Partida(
    private val dificultad: Dificultad,
    private val jugador: Aventurero,
    private val listaMonstruos: List<Monstruo>,
    private val accionesJugador: List<AccionJugador>
) {

    /**
     * Método que inicia la partida.
     */
    fun iniciar() {
        monstrarMensaje("\n¡Te enfrentarás a los monstruos de dificultad $dificultad!", brightMagenta)
        listaMonstruos.forEach { monstruo ->
            if (jugador.estaVivo()) {
                monstrarMensaje("\n¡Un ${monstruo.nombre} salvaje aparece!", brightRed)
                monstruo.mostrarStats()
                println()
                enfrentar(jugador, monstruo)
            } else {
                monstrarMensaje("${jugador.nombre} ha sido derrotado. ¡Fin de la partida!\n")
                return@forEach
            }
        }
        if (jugador.estaVivo()) {
            monstrarMensaje("\n¡Felicidades! Has derrotado a todos los monstruos.", brightCyan)
        }
    }

    /**
     * Método para enfrentar al jugador con un monstruo.
     *
     * @param jugador El aventurero que se enfrentará al monstruo.
     * @param monstruo El monstruo con el que se enfrentará el jugador.
     */
    private fun enfrentar(jugador: Aventurero, monstruo: Monstruo) {

        while (jugador.estaVivo() && monstruo.estaVivo()) {

            // Mostrar las diferentes acciones disponibles
            mostarAcciones(accionesJugador, jugador)
            val opcion = pedirOpcion(accionesJugador.size)
            accionesJugador[opcion - 1].ejecutar(jugador, monstruo)

            if (!monstruo.estaVivo()) {
                monstrarMensaje("¡El ${monstruo.nombre} ha sido derrotado!", brightBlue)
                val item = monstruo.soltarItem()
//                jugador.inventario.agregarItem(item) //Arreglar

            } else {
                //Turno del monstruo
                val danioMonstruo = monstruo.atacar()
                monstrarMensaje("El ${monstruo.nombre} ataca a ${jugador.nombre} con fuerza $danioMonstruo.", brightRed)
                jugador.recibirAtaque(danioMonstruo)

                if (!jugador.estaVivo()) {
                    monstrarMensaje("${jugador.nombre} ha sido derrotado. FIN DE LA PARTIDA\n")

                } else {
                    monstrarMensaje("\nEl ${monstruo.nombre} tiene ${monstruo.vida} puntos de vida restantes.", brightRed)
                    jugador.mostrarStats()
                }
            }
        }
    }
}