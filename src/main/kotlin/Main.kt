import Gestion.crearListaMonstruos
import Gestion.crearPersonaje
import Gestion.mostrarDificultadInfo
import Gestion.mostrarMensajeInicial
import Gestion.mostrarMenuDificultades
import Gestion.obtenerDificultad

fun main() {

    mostrarMensajeInicial() // Muestra el mensaje inicial
    mostrarMenuDificultades() // Muestra el menu de dificultades
    mostrarDificultadInfo() // Muestra info sobre dificultad

    val dificultad = obtenerDificultad()
    val jugador = crearPersonaje()

    println(jugador)

    val listaMonstruos = crearListaMonstruos(dificultad)
    val accionesDisponibles = listOf(AccionAtacar(), AccionCurar())
    val partida = Partida(dificultad, jugador, listaMonstruos, accionesDisponibles)

    partida.iniciar()
}