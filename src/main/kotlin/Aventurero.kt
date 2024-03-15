import Gestion.terminal
import com.github.ajalt.mordant.rendering.TextColors.*


/**
 * Clase abstracta que representa a un Aventurero en el juego.
 *
 * @property nombre El nombre del aventurero.
 * @property vida La cantidad de puntos de vida del aventurero.
 * @property fuerza La fuerza de ataque del aventurero.
 * @property defensa La defensa del aventurero.
 * @property pociones La cantidad de pociones de curación que posee el aventurero.
 */
abstract class Aventurero(
    val nombre: String,
    var vida: Int,
    var fuerza: Int,
    var defensa: Int,
    var pociones: Int,
) : Combate {

    val inventario: Inventario<Any> = Inventario()

    /**
     * Definición de propiedades y métodos estáticos relacionados con los aventureros.
     */
    companion object {
        const val VIDA_MAXIMA = 1000
        const val CURA_POCION = 200
    }

    /**
     * Método que determina la fuerza de ataque del aventurero.
     *
     * @return La fuerza de ataque del aventurero.
     */
    override fun atacar(): Int {
        return fuerza
    }

    /**
     * Método que registra el daño recibido por el aventurero y actualiza su vida en consecuencia.
     *
     * @param danio La cantidad de daño recibido.
     */
    override fun recibirAtaque(danio: Int) {
        val danioRecibido = danio - defensa
        if (danioRecibido > 0) {
            vida -= danioRecibido
        }

        if (vida < 0) {
            vida = 0
        }
    }

    /**
     * Método que verifica si el aventurero está vivo.
     *
     * @return true si el aventurero está vivo, false de lo contrario.
     */
    override fun estaVivo(): Boolean = vida > 0


    /**
     * Método para que el aventurero se cure utilizando una poción, si tiene alguna disponible.
     * Restaura 200 puntos de vida.
     */
    open fun curarse() {
        if (pociones > 0) {
            vida = if (vida + CURA_POCION > VIDA_MAXIMA) VIDA_MAXIMA else vida + CURA_POCION  // Cura 200 puntos de vida
            pociones--
            terminal.println(brightGreen("$nombre se ha curado 200 puntos de vida."))
        } else {
            terminal.println(brightRed("$nombre no tiene pociones."))
        }
    }

    /**
     * Método abstracto para mostrar el menú de ataques del aventurero.
     */
    abstract fun mostrarMenuAtaques()

}