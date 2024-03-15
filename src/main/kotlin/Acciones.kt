import Gestion.monstrarMensaje
import com.github.ajalt.mordant.rendering.BorderType
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal

//Inetento de principio solid rarete

/**
 * Interfaz que define una acción que puede realizar un jugador en el juego.
 */
interface AccionJugador {
    fun ejecutar(jugador: Aventurero, objetivo: Monstruo)
}

/**
 * Clase que representa la acción de atacar a un monstruo.
 */
class AccionAtacar : AccionJugador {
    override fun ejecutar(jugador: Aventurero, objetivo: Monstruo) {
        val danio = jugador.atacar()
        if (danio > 0) {
            // Realizar el ataque al objetivo
            monstrarMensaje("${jugador.nombre} ataca a ${objetivo.nombre} con fuerza $danio.", TextColors.brightMagenta)
            // Realizar el ataque al objetivo
            objetivo.recibirAtaque(danio)
        }
    }
}

/**
 * Clase que representa la acción de curar al jugador.
 */
class AccionCurar : AccionJugador {
    override fun ejecutar(jugador: Aventurero, objetivo: Monstruo) {
        // Ejecutar la acción de curación del aventurero
        jugador.curarse()
    }
}

/**
 * Función para mostrar las acciones disponibles para el jugador.
 *
 * @param accionesJugador La lista de acciones disponibles para el jugador.
 * @param jugador El aventurero que está realizando el turno.
 */
fun mostarAcciones(accionesJugador: List<AccionJugador>, jugador: Aventurero) {
    monstrarMensaje("Turno de ${jugador.nombre}", TextColors.brightMagenta)
    monstrarMensaje("Selecciona una acción:", TextColors.brightMagenta)

    val tablaAcciones = table {
        borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
        borderStyle = TextColors.rgb("#4b25b9")
        header {
            row(TextColors.brightWhite("Opcion"), TextColors.brightWhite("Accion")) { style = TextColors.brightMagenta }
        }
        body {
            accionesJugador.forEachIndexed { index, accion ->
                row("${index + 1}.", accion.javaClass.simpleName.replace("Accion", "")) { style = TextColors.entries[index + 1] }
            }
        }
        style = TextColors.brightWhite
    }
    val terminal = Terminal()
    terminal.println(tablaAcciones)
}


//Implementar una accion para ver los items que tiene el jugador en el inventario