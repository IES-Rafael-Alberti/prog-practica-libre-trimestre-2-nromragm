import Terminal.terminal
import com.github.ajalt.mordant.rendering.TextColors.*

class Partida(
    private val dificultad: Dificultad,
    private val jugador: Aventurero,
    private val listaMonstruos: List<Monstruo>,
    private val accionesJugador: List<AccionJugador>
) {

    fun iniciar() {
        println()
        terminal.println(brightMagenta("¡Te enfrentarás a los monstruos de dificultad $dificultad!"))
        listaMonstruos.forEach { monstruo ->
            if (jugador.estaVivo()) {
                terminal.println(brightRed("\n¡Un ${monstruo.nombre} salvaje aparece!"))
                monstruo.mostrarStats()
                println()
                enfrentar(jugador, monstruo)
            } else {
                println("${jugador.nombre} ha sido derrotado. ¡Fin de la partida!")
                return@forEach
            }
        }
        if (jugador.estaVivo()) {
            println("¡Felicidades! Has derrotado a todos los monstruos.")
        }
    }

    private fun enfrentar(jugador: Aventurero, monstruo: Monstruo) {
        while (jugador.estaVivo() && monstruo.estaVivo()) {

            terminal.println(brightMagenta("Turno de ${jugador.nombre}"))
            terminal.println(brightMagenta("Selecciona una acción:"))

            // Mostrar las diferentes acciones disponibles
            mostarAcciones(accionesJugador)

            val opcion = pedirOpcion(accionesJugador.size)

            // Ejecutar accion
            accionesJugador[opcion - 1].ejecutar(jugador, monstruo)

            if (!monstruo.estaVivo()) {
                terminal.println(brightBlue("¡El ${monstruo.nombre} ha sido derrotado!"))
                break
            }

            val danioMonstruo = monstruo.atacar()
            terminal.println(brightRed("El ${monstruo.nombre} ataca a ${jugador.nombre} con fuerza $danioMonstruo."))
            jugador.recibirAtaque(danioMonstruo)

            if (!jugador.estaVivo()) {
                println("${jugador.nombre} ha sido derrotado. FIN DE LA PARTIDA")
                break
            }
            println()

            terminal.println(brightRed("El ${monstruo.nombre} tiene ${monstruo.vida} puntos de vida restantes."))

            jugador.mostrarStats()
        }
    }
}
