import Terminal.terminal
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import kotlin.random.Random
import com.github.ajalt.mordant.rendering.TextColors.*

class Mago(nombre: String) : Aventurero(nombre, 800, 15, 5, 3) {
    private var mana: Int = MANA_MAXIMO
    private var haUsadoHabilidadDefinitiva = false

    companion object {
        const val MANA_MAXIMO = 500
        const val DANIO_HABILIDAD_DEFINITIVA = 999
    }

    private fun esCritico(): Boolean {
        return Random.nextInt(10) <= 3
    }


    private fun hechizoBolaDeFuego(): Int {
        val costoMana = 30
        return if (puedeLanzarHechizo(costoMana)) {
            gastarMana(costoMana)
            if (esCritico()) {
                // Ataque crítico
                terminal.println(brightRed("CRITICO!"))
                fuerza * 3
            } else {
                // Ataque normal
                fuerza * 2
            }
        } else {
            println("No tienes suficiente mana para lanzar Bola de Fuego.")
            0
        }
    }

    private fun hechizoRayo(): Int {
        val costoMana = 50

        return if (puedeLanzarHechizo(costoMana)) {
            gastarMana(costoMana)
            if (esCritico()) {
                // Ataque crítico
                println("CRITICO!")
                fuerza * 4
            } else {
                // Ataque normal
                fuerza * 3
            }
        } else {
            println("No tienes suficiente mana para lanzar Rayo.")
            0
        }
    }

    private fun habilidadDefinitiva(): Int {

        if (!haUsadoHabilidadDefinitiva) {

            terminal.println(brightMagenta("¡${nombre} ha lanzado su habilidad definitiva! DEVORA ALMAS."))
            haUsadoHabilidadDefinitiva = true
            mana = MANA_MAXIMO

            return DANIO_HABILIDAD_DEFINITIVA

        } else {
            println("Ya has usado tu habilidad definitiva.")
            return 0
        }
    }


    private fun puedeLanzarHechizo(costoMana: Int): Boolean {
        return mana >= costoMana
    }

    private fun gastarMana(costoMana: Int) {
        mana -= costoMana
    }

    override fun mostrarMenuAtaques() {
        val menu = table {
            header {
                row("Hechizo", "Info")
                style = brightWhite
                align = TextAlign.CENTER
            }
            body {
                row {
                    cells("1. Bola", "Lanza una bola de fuego que consume 35 mana")
                    style = brightRed
                    cellBorders = Borders.LEFT_TOP_RIGHT
                }
                row {
                    cells("de fuego", "y tiene el doble de fuerza base")
                    style = brightRed
                    cellBorders = Borders.LEFT_RIGHT_BOTTOM
                }
                row {
                    cells("2.", "Lanza un Rayo que consume 50 mana")
                    style = brightYellow
                    cellBorders = Borders.LEFT_TOP_RIGHT
                }
                row {
                    cells("Rayo", "y tiene el doble de fuerza base")
                    style = brightYellow
                    cellBorders = Borders.LEFT_RIGHT_BOTTOM
                }
                row {
                    cells("3. Habilidad", "Absorbes su alma matando al Monstruo")
                    style = brightBlue
                    cellBorders = Borders.LEFT_TOP_RIGHT
                }
                row {
                    cells("definitiva", "y recuperas todo el mana")
                    style = brightBlue
                    cellBorders = Borders.LEFT_RIGHT_BOTTOM
                }
                align = TextAlign.CENTER
            }
        }
        terminal.println(menu)
    }

    override fun atacar(): Int {
        mostrarMenuAtaques()

        val opcion = pedirOpcion(3)

        return when (opcion) {
            1 -> hechizoBolaDeFuego()
            2 -> hechizoRayo()
            else -> habilidadDefinitiva()
        }
    }


    override fun toString(): String {
        return "Mago(Nombre: $nombre, Vida: $vida, Fuerza: $fuerza , Defensa: $defensa, Mana: $mana, Pociones: $pociones)"
    }

    /**
     * Muestra los atributos del mago en una tabla con colores personalizados para el borde y el estilo de la tabla.
     */
    override fun mostrarStats() {
        println()
        terminal.println(brightWhite("Stats de $nombre"))
        val stats = table {
            borderStyle = brightGreen
            style = brightWhite
            header {
                row("Atributo", "Valor")
                style = TextColors.rgb("#4b25b9")
            }
            body {
                row("Nombre", nombre)
                row("Vida", vida.toString())
                row("Fuerza", fuerza.toString())
                row("Defensa", defensa.toString())
                row("Mana", mana.toString())
                row ("Pociones", pociones.toString())
                row("H.Definitiva", if (haUsadoHabilidadDefinitiva) "false" else "true")
            }
        }
        terminal.println(stats)
        println()
    }
}