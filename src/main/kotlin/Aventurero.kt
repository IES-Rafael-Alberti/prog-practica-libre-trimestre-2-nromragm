import Terminal.terminal
import com.github.ajalt.mordant.rendering.TextColors.*

abstract class Aventurero(
    val nombre: String,
    var vida: Int,
    var fuerza: Int,
    var defensa: Int,
    var pociones: Int
) : Combate {

    companion object {
        const val VIDA_MAXIMA = 1000
        const val CURA_POCION = 200
    }

    override fun atacar(): Int {
        return fuerza
    }

    override fun recibirAtaque(danio: Int) {
        val danioRecibido = danio - defensa
        if (danioRecibido > 0) {
            vida -= danioRecibido
        }

        if (vida < 0) {
            vida = 0
        }
    }

    override fun estaVivo(): Boolean {
        return vida > 0
    }

    open fun curarse() {
        if (pociones > 0) {
            vida = if (vida + CURA_POCION > VIDA_MAXIMA) VIDA_MAXIMA else vida + CURA_POCION  // Cura 200 puntos de vida
            pociones--
            terminal.println(brightBlue("$nombre se ha curado 200 puntos de vida."))
        } else {
            terminal.println(brightRed("$nombre no tiene pociones."))
        }

    }

    abstract fun mostrarMenuAtaques()

}