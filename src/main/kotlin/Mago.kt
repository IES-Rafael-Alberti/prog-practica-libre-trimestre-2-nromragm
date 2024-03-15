import Gestion.pedirOpcion
import Gestion.terminal
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import kotlin.random.Random
import com.github.ajalt.mordant.rendering.TextColors.*


/**
 * Clase que representa a un mago, una subclase de [Aventurero].
 *
 * @param nombre El nombre del mago.
 */
class Mago(nombre: String) : Aventurero(nombre, 800, 15, 5, 3) {
    private var mana: Int = MANA_MAXIMO
    private var haUsadoHabilidadDefinitiva = false

    companion object {
        const val MANA_MAXIMO = 500
        const val MANA_POCION = 100
        const val DANIO_HABILIDAD_DEFINITIVA = 999
        const val VIDA_MAXIMA = 800
    }

    /**
     * Verifica si un ataque realizado por el mago es crítico.
     *
     * @return true si el ataque es crítico, false en caso contrario.
     */
    fun esCritico(): Boolean {
        return Random.nextInt(10) <= 3
    }


    /**
     * Realiza el hechizo de la Bola de Fuego.
     *
     * @return El daño infligido por el hechizo.
     */
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

    /**
     * Realiza el hechizo del Rayo.
     *
     * @return El daño infligido por el hechizo.
     */
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

    /**
     * Realiza la habilidad definitiva del mago.
     *
     * @return El daño infligido por la habilidad definitiva.
     */
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

    /**
     * Permite al mago curarse utilizando una poción.
     * Restaura tanto la vida como el mana del mago si tiene pociones disponibles.
     */
    override fun curarse() {
        if (pociones > 0) {
            val vidaPocion = if (vida + CURA_POCION > VIDA_MAXIMA) VIDA_MAXIMA - vida else CURA_POCION  // Cura 200 puntos de vida
            vida += vidaPocion
            val manaPocion = if (mana + MANA_POCION > MANA_MAXIMO) MANA_MAXIMO - mana else MANA_POCION // Recupera 100 de mana
            mana += manaPocion
            pociones--
            terminal.println(brightGreen("$nombre se ha curado $vidaPocion puntos de vida."))
            terminal.println(brightGreen("$nombre ha recuperado $manaPocion de mana."))
        } else {
            terminal.println(brightRed("$nombre no tiene pociones."))
        }
    }

    /**
     * Verifica si el mago puede lanzar un hechizo dado el costo de mana.
     *
     * @param costoMana El costo de mana del hechizo.
     * @return true si el mago tiene suficiente mana para lanzar el hechizo, false en caso contrario.
     */
    fun puedeLanzarHechizo(costoMana: Int): Boolean {
        return mana >= costoMana
    }

    /**
     * Reduce el mana del mago al lanzar un hechizo.
     *
     * @param costoMana El costo de mana del hechizo.
     */
    private fun gastarMana(costoMana: Int) {
        mana -= costoMana
    }

    /**
     * Muestra el menú de ataques del mago, incluyendo los hechizos disponibles y su información.
     */
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

    /**
     * Realiza un ataque utilizando los hechizos del mago.
     *
     * @return El daño infligido por el hechizo seleccionado.
     */
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