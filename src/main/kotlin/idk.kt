import Terminal.terminal
import com.github.ajalt.mordant.animation.textAnimation
import com.github.ajalt.mordant.rendering.BorderType
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyles
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import kotlin.random.Random
import com.github.ajalt.mordant.rendering.TextColors.*
import java.util.*


fun generarNombre(): String {
    var nombre = ""
    var salir = false

    while (!salir) {
        println()
        terminal.println(brightMagenta("Introduce un nombre para tu personaje"))
        terminal.print("👉 ")
        nombre = readln()
        if (nombre.isBlank()) {
            terminal.println(brightWhite("El nombre no puede ser una cadena vacia"))
        } else {
            salir = true
        }
    }
    return (nombre)
}
fun pedirOpcion(max: Int): Int {
    var opcion = 0
    var salir = false

    while (!salir) {
        try {
            terminal.print("👉 ")
            opcion = readln().toInt()
            if (opcion <= 0 || opcion > max) {
                throw NumberFormatException()
            } else {
                salir = true
            }
        } catch (e: NumberFormatException) {
            terminal.println(brightWhite("Introduce un numero válido"))
        }
    }
    return opcion
}
fun mostrarMenuClases() {

    val tabla = table {
        borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
        borderStyle = TextColors.rgb("#4b25b9")
        align = TextAlign.LEFT
        tableBorders = Borders.ALL
        header {
            style = brightWhite + TextStyles.bold
            row {
                cell("Selecciona una clase")
            }

        }
        body {
            row {
                cell("Opcion 1. MAGO")
                style = brightGreen
            }
            row {
                cell("Opcion 2. GUERRERO")
                style = brightBlue
            }
        }
    }
    println()
    terminal.println(tabla)
}

fun crearPersonaje(): Aventurero {

    mostrarMenuClases()
    val opcion = pedirOpcion(2)

    return when (opcion) {
        1 -> {
            terminal.println(brightGreen("Has elegido la clase Mago."))
            val nombre = generarNombre().capitalizar()
            Mago(nombre)
        }
        else -> {
            terminal.println(brightBlue("Has elegido la clase Guerrero."))
            val nombre = generarNombre().capitalizar()
            Guerrero(nombre)
        }
    }
}

fun crearListaMonstruos(dificultad: Dificultad): List<Monstruo> {
    val listaMonstruos = mutableListOf<Monstruo>()
    val nombres = listOf("Dragon 🐉", "Esqueleto 💀", "Demonio 👹", "Cthulhu 🦑")

    val vidaMinima: Int
    val vidaMaxima: Int
    val fuerzaMinima: Int
    val fuerzaMaxima: Int
    val defensaMinima: Int
    val defensaMaxima: Int
    val tiposMonstruo: List<TipoMonstruo>
    val cantidad: Int

    when (dificultad) {
        Dificultad.FACIL -> {
            vidaMinima = 50
            vidaMaxima = 76
            fuerzaMinima = 10
            fuerzaMaxima = 21
            defensaMinima = 5
            defensaMaxima = 11
            tiposMonstruo = listOf(TipoMonstruo.NORMAL)
            cantidad = 3
        }
        Dificultad.NORMAL -> {
            vidaMinima = 75
            vidaMaxima = 101
            fuerzaMinima = 15
            fuerzaMaxima = 26
            defensaMinima = 8
            defensaMaxima = 16
            tiposMonstruo = listOf(TipoMonstruo.NORMAL, TipoMonstruo.entries.random())
            cantidad = 4
        }
        Dificultad.DIFICIL -> {
            vidaMinima = 100
            vidaMaxima = 151
            fuerzaMinima = 20
            fuerzaMaxima = 31
            defensaMinima = 10
            defensaMaxima = 21
            tiposMonstruo = listOf(TipoMonstruo.VENENOSO, TipoMonstruo.BERSERKER, TipoMonstruo.VAMPIRO, TipoMonstruo.ACORAZADO)
            cantidad = 5
        }
    }

    for (i in 1..cantidad) {
        val nombreRandom = nombres.random()
        val vidaRandom = Random.nextInt(vidaMinima, vidaMaxima)
        val fuerzaRandom = Random.nextInt(fuerzaMinima, fuerzaMaxima)
        val defensaRandom = Random.nextInt(defensaMinima, defensaMaxima)
        val tipoMonstruoRandom = tiposMonstruo.random()

        val monstruo = Monstruo(nombreRandom, vidaRandom, fuerzaRandom, defensaRandom, tipoMonstruoRandom)
        listaMonstruos.add(monstruo)
    }

    return listaMonstruos
}

fun mostrarMenuDificultades() {

    val tablaDificultad = table {
        borderType = BorderType.SQUARE_DOUBLE_SECTION_SEPARATOR
        borderStyle = TextColors.rgb("#4b25b9")
        align = TextAlign.LEFT
        tableBorders = Borders.ALL
        header {
            style = brightWhite + TextStyles.bold
            row {
                cell("Selecciona la dificultad")
            }

        }
        body {
            row {
                cell("Opcion 1. FACIL")
                style = brightGreen
            }
            row {
                cell("Opcion 2. NORMAL")
                style = brightBlue
            }
            row {
                cell("Opcion 3. DIFICIL")
                style = brightRed
            }
        }
    }

    println()
    terminal.println(tablaDificultad)
}

fun mostrarMensajeInicial(){
    val introText = buildString {
        appendLine(brightMagenta("¡Bienvenido a Kill 'Em All!"))
        appendLine()
        appendLine(TextStyles.bold("En este minijuego, te enfrentarás a desafiantes monstruos."))
        appendLine(TextStyles.bold("Tu objetivo es derrotarlos a todos los monstruos y convertirte en el héroe legendario."))
    }
    terminal.println(introText)
    val a = terminal.textAnimation<Int> { frame ->
        (1..50).joinToString("") {
            val hue = (frame + it) * 3 % 360
            TextColors.hsv(hue, 1, 1)("━")
        }
    }

    terminal.cursor.hide(showOnExit = true)
    repeat(120) {
        a.update(it)
        Thread.sleep(25)
    }
    a.stop()
    println()
}
fun mostrarDificultadInfo() {
    terminal.println(brightCyan("Segun la dificultad que elijas los monstruos serán más numerosos y más poderosos."))
}

fun obtenerDificultad(): Dificultad {
    val opcionDificultad = pedirOpcion(3)
    return when (opcionDificultad) {
        1 -> Dificultad.FACIL
        2 -> Dificultad.NORMAL
        else -> Dificultad.DIFICIL
    }
}

/**
 * Extiende la clase [String] para permitir capitalizar cada palabra en una cadena de caracteres.
 * Divide la cadena en palabras utilizando espacios como delimitadores,
 * luego capitaliza la primera letra de cada palabra y las une nuevamente en una cadena.
 *
 * @return Un [String] donde cada palabra está capitalizada.
 */
fun String.capitalizar(): String {
    return this.split(" ").joinToString(" ") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
}