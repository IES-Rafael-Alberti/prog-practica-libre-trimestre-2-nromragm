import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Test {

    @Test
    fun estaVivoTest() {
        val mago = Mago("hola")
        assertEquals(true, mago.estaVivo())

        val mago2 = Mago("adios")
        mago2.vida = 0
        assertEquals(false, mago2.estaVivo())
    }

    @Test
    fun puedeLanzarHechizoTest() {
        val magito = Mago("Test")
        assertEquals(true, magito.puedeLanzarHechizo(50))
        assertEquals(false, magito.puedeLanzarHechizo(9999))
    }
}