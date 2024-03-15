//import Gestion.listaItems
import Gestion.terminal
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.rendering.TextColors.*

/**
 * Representa a un monstruo en el juego.
 *
 * @property nombre El nombre del monstruo.
 * @property vida La cantidad de vida actual del monstruo.
 * @property fuerza La fuerza del monstruo, que determina el daño que puede infligir.
 * @property defensa La defensa del monstruo, que reduce el daño recibido.
 * @property tipoMonstruo El tipo de monstruo, que puede afectar su comportamiento en combate.
 */
class Monstruo(
    val nombre: String,
    var vida: Int,
    private var fuerza: Int,
    private var defensa: Int,
    private val tipoMonstruo: TipoMonstruo = TipoMonstruo.NORMAL,

) : Combate {
    //Lista de items que iba a soltar al morir
//    private val item: Item<out Any> = listaItems.random()
    /**
     * Realiza un ataque del monstruo.
     * Varia segun el tipo de monstruo
     *
     * @return El daño infligido por el ataque.
     */
    override fun atacar(): Int {

        // Si el monstruo es un vampiro, aumenta su vida al atacar
        if (tipoMonstruo == TipoMonstruo.VAMPIRO) vida += (fuerza * 0.2).toInt()

        // Determina el daño base según el tipo de monstruo
        return when (tipoMonstruo) {
            TipoMonstruo.VENENOSO -> fuerza + 5 // Aumenta la fuerza para monstruos venenosos
            TipoMonstruo.BERSERKER -> (fuerza * 1.5).toInt() // Aumenta la fuerza para monstruos berserkers un 50%
            else -> fuerza // Otros tipos de monstruos mantienen su fuerza base
        }
    }

    /**
     * Recibe un ataque del oponente.
     * Varia segun el tipo de monstruo
     *
     * @param danio El daño infligido por el oponente.
     */
    override fun recibirAtaque(danio: Int) {

        // Calcula el daño recibido considerando la defensa del monstruo y su tipo
        val danioRecibido = danio - when (tipoMonstruo) {
            TipoMonstruo.BERSERKER -> 0 // Berserkers no tienen defensa
            TipoMonstruo.ACORAZADO -> (defensa * 1.5).toInt() // Acorazados tienen un 50% mas defensa
            else -> defensa // Otros tipos de monstruos usan su defensa base
        }

        // Reducir la vida del monstruo según el daño recibido
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

//    fun soltarItem() = item   //Metodo para soltar el item

    /**
     * Sobrescribe el método toString de la clase [Monstruo] para ofrecer una representación textual detallada del monstruo,
     * incluyendo su nombre, vida, fuerza, defensa y tipo.
     *
     * @return Una representación en cadena de texto del monstruo, detallando sus stats.
     */
    override fun toString(): String {
        return "Monstruo(Nombre: $nombre, Vida: $vida, Fuerza: $fuerza, Defensa: $defensa, Tipo: $tipoMonstruo)"
    }

    override fun mostrarStats() {
        // Crear la tabla
        val stats = table {
            borderStyle = brightRed
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
                row("Tipo", tipoMonstruo)
            }
        }
        terminal.println(stats)
        println()
    }
}