import Gestion.crearListaMonstruos
import Gestion.crearPersonaje
import Gestion.monstrarMensaje
import Gestion.mostrarDificultadInfo
import Gestion.mostrarMensajeInicial
import Gestion.mostrarMenuDificultades
import Gestion.obtenerDificultad
import Gestion.terminal
import com.github.ajalt.mordant.rendering.BorderType
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table

interface AccionJugador {
    fun ejecutar(jugador: Aventurero, objetivo: Monstruo)
}

class AccionAtacar : AccionJugador {
    override fun ejecutar(jugador: Aventurero, objetivo: Monstruo) {
        val danio = jugador.atacar()
        if (danio > 0) {
            monstrarMensaje("${jugador.nombre} ataca a ${objetivo.nombre} con fuerza $danio.", brightMagenta)
            objetivo.recibirAtaque(danio)
        }
    }
}

class AccionCurar : AccionJugador {
    override fun ejecutar(jugador: Aventurero, objetivo: Monstruo) {
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
    monstrarMensaje("Turno de ${jugador.nombre}", brightMagenta)
    monstrarMensaje("Selecciona una acción:", brightMagenta)

    val tablaAcciones = table {
        borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
        borderStyle = TextColors.rgb("#4b25b9")
        header {
            row(brightWhite("Opcion"), brightWhite("Accion")) { style = brightMagenta }
        }
        body {
            accionesJugador.forEachIndexed { index, accion ->
                row("${index + 1}.", accion.javaClass.simpleName.replace("Accion", "")) { style = TextColors.entries[index + 1] }
            }
        }
        style = brightWhite
    }
    terminal.println(tablaAcciones)
}

fun main() {

    mostrarMensajeInicial()
    mostrarMenuDificultades()
    mostrarDificultadInfo()

    val dificultad = obtenerDificultad()
    val jugador = crearPersonaje()

    println(jugador)

    val listaMonstruos = crearListaMonstruos(dificultad)
    val accionesDisponibles = listOf(AccionAtacar(), AccionCurar())
    val partida = Partida(dificultad, jugador, listaMonstruos, accionesDisponibles)

    partida.iniciar()
}