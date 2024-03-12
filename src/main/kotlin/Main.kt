import Terminal.terminal
import com.github.ajalt.mordant.rendering.BorderType
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table

interface AccionJugador {
    fun ejecutar(jugador: Aventurero, objetivo: Monstruo)
}

class AccionAtacar : AccionJugador {
    override fun ejecutar(jugador: Aventurero, objetivo: Monstruo) {
        val danio = jugador.atacar()
        if (danio > 0) {
            terminal.println(TextColors.brightMagenta("${jugador.nombre} ataca a ${objetivo.nombre} con fuerza $danio."))
            objetivo.recibirAtaque(danio)
        }
    }
}

class AccionCurar : AccionJugador {
    override fun ejecutar(jugador: Aventurero, objetivo: Monstruo) {
        jugador.curarse()
    }
}

fun mostarAcciones(accionesJugador: List<AccionJugador>) {
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