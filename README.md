[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/mnkKwimk)
# Actividad: Desarrollo de Proyecto Software en Kotlin

**ID actividad:** 2324_PRO_u4u5u6_libre

**Agrupamiento de la actividad**: Individual 

---

### Descripción:

La actividad consiste en el desarrollo de un proyecto software en Kotlin, permitiendo al estudiante elegir la temática. Este proyecto debe demostrar la comprensión y aplicación de los conceptos de programación orientada a objetos (POO), incluyendo la definición y uso de clases, herencia, interfaces, genericos, principios SOLID y el uso de librerías externas.

**Objetivo:**

- Demostrar comprensión de los fundamentos de POO mediante la instanciación y uso de objetos.
- Aplicar conceptos avanzados de POO como herencia, clases abstractas, e interfaces.
- Crear y usar clases que hagan uso de genéricos. 
- Aplicar principios SOLID.
- Integrar y utilizar librerías de clases externas para extender la funcionalidad del proyecto.
- Documentar y presentar el código de manera clara y comprensible.

**Trabajo a realizar:**

1. **Selección de la Temática:** Elige un tema de tu interés que pueda ser abordado mediante una aplicación software. Esto podría ser desde una aplicación de gestión para una pequeña empresa, una herramienta para ayudar en la educación, hasta un juego simple. Define claramente el problema que tu aplicación pretende resolver.

2. **Planificación:** Documenta brevemente cómo tu aplicación solucionará el problema seleccionado, incluyendo las funcionalidades principales que desarrollarás.

3. **Desarrollo:**
   - **Instancia de Objetos:** Tu aplicación debe crear y utilizar objetos, demostrando tu comprensión de la instanciación y el uso de constructores, métodos, y propiedades.
   - **Métodos Estáticos:** Define y utiliza al menos un método estático, explicando por qué es necesario en tu aplicación.
   - **Uso de IDE:** Desarrolla tu proyecto utilizando un IDE, aprovechando sus herramientas para escribir, compilar, y probar tu código.
   - **Definición de Clases:** Crea clases personalizadas con sus respectivas propiedades, métodos, y constructores.
   - **Clases con genéricos:** Define y utiliza al menos una clase que haga uso de genéricos en tu aplicación.
   - **Herencia y Polimorfismo:** Implementa herencia y/o interfaces en tu proyecto para demostrar la reutilización de código y la flexibilidad de tu diseño.  **Usa los principios SOLID:** Ten presente durante el desarrollo los principios SOLID y úsalos durante el diseño para mejorar tu aplicación.
   - **Librerías de Clases:** Integra y utiliza una o más librerías externas que enriquezcan la funcionalidad de tu aplicación.
   - **Documentación:** Comenta tu código de manera efectiva, facilitando su comprensión y mantenimiento.

4. **Prueba y Depuración:** Realiza pruebas para asegurarte de que tu aplicación funciona como se espera y depura cualquier error encontrado.
5. **Contesta a las preguntas** ver el punto **Preguntas para la Evaluación**

### Recursos

- Apuntes dados en clase sobre programación orientada a objetos, Kotlin, uso de IDEs, y manejo de librerías.
- Recursos vistos en clase, incluyendo ejemplos de código, documentación de Kotlin, y guías de uso de librerías.

### Evaluación y calificación

**RA y CE evaluados**: Resultados de Aprendizaje 2, 4, 6, 7 y Criterios de Evaluación asociados.

**Conlleva presentación**: SI

**Rubrica**: Mas adelante se mostrará la rubrica.

### Entrega

> **La entrega tiene que cumplir las condiciones de entrega para poder ser calificada. En caso de no cumplirlas podría calificarse como no entregada.**
>
- **Conlleva la entrega de URL a repositorio:** El contenido se entregará en un repositorio GitHub. 
- **Respuestas a las preguntas:** Deben contestarse en este fichero, README.md


# Preguntas para la Evaluación

Este conjunto de preguntas está diseñado para ayudarte a reflexionar sobre cómo has aplicado los criterios de evaluación en tu proyecto. Al responderlas, **asegúrate de hacer referencia y enlazar al código relevante** en tu `README.md`, facilitando así la evaluación de tu trabajo.

#### **Criterio global 1: Instancia objetos y hacer uso de ellos**
- **(2.a, 2.b, 2.c, 2.d, 2.f, 2.h, 4.f, 4.a)**: Describe cómo has instanciado y utilizado objetos en tu proyecto. ¿Cómo has aplicado los constructores y pasado parámetros a los métodos? Proporciona ejemplos específicos de tu código.
- **Respuesta**: He instanciado objetos para representar entidades del juego por ejemplo el jugador, para instanciarlo he llamado al constructor con los paramentros necesarios a la hora de crear el personaje
```Kotlin
    fun crearPersonaje(): Aventurero {

        mostrarMenuClases()
        val opcion = pedirOpcion(2)

        return when (opcion) {
            1 -> {
                monstrarMensaje("Has elegido la clase Mago.", brightGreen)
                val nombre = generarNombre().capitalizar()
                Mago(nombre)
            }
```
[Link del repo](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-nromragm/blob/089ffe32adedfc637328aedc05c16001e905cbe5/src/main/kotlin/Gestion.kt#L118-L127)

---

#### **Criterio global 2: Crear y llamar métodos estáticos**
- **(4.i)**: ¿Has definido algún método/propiedad estático en tu proyecto? ¿Cuál era el objetivo y por qué consideraste que debía ser estático en lugar de un método/propiedad de instancia?
- **Respuesta**: El objetivo era crear constantes como la vida maxima o la cura de la pocion
```Kotlin
    companion object {
        const val MANA_MAXIMO = 500
        const val MANA_POCION = 100
        const val DANIO_HABILIDAD_DEFINITIVA = 999
        const val VIDA_MAXIMA = 800
    }
```
[Link del repo](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-nromragm/blob/089ffe32adedfc637328aedc05c16001e905cbe5/src/main/kotlin/Mago.kt#L20-L24)

- **(2.e)**: ¿En qué parte del código se llama a un método estático o se utiliza la propiedad estática?
```Kotlin
    open fun curarse() {
        if (pociones > 0) {
            vida = if (vida + CURA_POCION > VIDA_MAXIMA) VIDA_MAXIMA else vida + CURA_POCION  // Cura 200 puntos de vida
            pociones--
            terminal.println(brightGreen("$nombre se ha curado 200 puntos de vida."))
        } else {
            terminal.println(brightRed("$nombre no tiene pociones."))
        }
    }
```
[Link del repo](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-nromragm/blob/089ffe32adedfc637328aedc05c16001e905cbe5/src/main/kotlin/Aventurero.kt#L70-L78)

---

#### **Criterio global 3: Uso de entornos**
- **(2.i)**: ¿Cómo utilizaste el IDE para el desarrollo de tu proyecto? Describe el proceso de creación, compilación, y prueba de tu programa.
- **Respuesta**: He utilezado IntelliJ IDEA como entorno de desarrollo y he utilizado este IDE para crear un nuevo proyecto de kotlin implementando el codigo del juego y realizando pruebas depurando

---

#### **Criterio global 4: Definir clases y su contenido**
- **(4.b, 4.c, 4.d, 4.g)**: Explica sobre un ejemplo de tu código, cómo definiste las clases en tu proyecto, es decir como identificaste las de propiedades, métodos y constructores y modificadores del control de acceso a métodos y propiedades, para representar al objeto del mundo real. ¿Cómo contribuyen estas clases a la solución del problema que tu aplicación aborda?
- **Respuesta**: Defini las clases identificando las propiedades y metodos necesarios para representar objetos como aventureros, monstruos... Las clases tienen constructores para inicializar sus propiedades y metodos para realizar acciones. Por ejemplo, la clase Mago tiene propiedades como nombre, vida, y metodos como curarse(), atacar()

---

#### **Criterio global 5: Herencia y uso de clases abstractas e interfaces**
- **(4.h, 4.j, 7.a, 7.b, 7.c)**: Describe sobre tu código cómo has implementado la herencia o utilizado interfaces en tu proyecto. ¿Por qué elegiste este enfoque y cómo beneficia a la estructura de tu aplicación? ¿De qué manera has utilizado los principios SOLID para mejorar el diseño de tu proyecto? ¿Mostrando tu código, contesta a qué principios has utilizado y qué beneficio has obtenido?
- **Respuesta**: He utilizado la herencia para compartir comportamientos y propiedades comunes entre clases relacionadas. Por ejemplo, tengo una clase abstracta Aventurero de la cual heredan las clases Mago y Guerrero, ya que ambos comparten ciertas características y comportamientos de un aventurero. También he utilizado interfaces para definir comportamientos comunes que varias clases implementan. Por ejemplo, tengo una interfaz Combatiente que definen metodos como atacar(), recibirAtaque()... que pueden realizar tanto el aventurero como los monstruos. Y por ultimo he intentado aplicar de forma correctas los principios SOLID haciendo que cada clase tenga una responsabilidad unica o intentando aplicar el principio abierto/cerrado (OCP)
```Kotlin
abstract class Aventurero(
    val nombre: String,
    var vida: Int,
    var fuerza: Int,
    var defensa: Int,
    var pociones: Int,
) : Combate {

/**
 * Interfaz que define las operaciones de combate para un personaje o monstruos en el juego.
 */
interface Combate {
    fun atacar(): Int
    fun recibirAtaque(danio: Int)
    fun estaVivo(): Boolean
    fun mostrarStats()
}
```
[Link del repo](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-nromragm/blob/089ffe32adedfc637328aedc05c16001e905cbe5/src/main/kotlin/Aventurero.kt#L14-L20)
[Link del repo, Interfaz](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-nromragm/blob/089ffe32adedfc637328aedc05c16001e905cbe5/src/main/kotlin/Combate.kt#L4-L9)

---

#### **Criterio global 6: Diseño de jerarquía de clases**
- **(7.d, 7.e, 7.f, 7.g)**: Presenta la jerarquía de clases que diseñaste. ¿Cómo probaste y depuraste esta jerarquía para asegurar su correcto funcionamiento? ¿Qué tipo de herencia has utilizado: Especificación, Especialización, Extensión, Construcción?
- **Respuesta**: He utilizado la herencia por especialización. Este tipo de herencia implica que las clases derivadas (subclases) son una forma más específica o especializada de la clase base (superclase). Tengo una superclase abstracta Aventurero y dos subclases Mago y Guerrero(no implementada) que son tipos especificos de aventurero

---

#### **Criterio global 7: Librerías de clases**
- **(2.g, 4.k)**: Describe cualquier librería externa que hayas incorporado en tu proyecto. Explica cómo y por qué las elegiste, y cómo las incorporaste en tu proyecto. ¿Cómo extendió la funcionalidad de tu aplicación? Proporciona ejemplos específicos de su uso en tu proyecto.
- **Respuesta**: He usado Mordant que proporciona utilidades para el formato de texto y la impresion en la consola de forma mas avanzada y con colores.
```Kotlin
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
```
[Link del repo](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-nromragm/blob/089ffe32adedfc637328aedc05c16001e905cbe5/src/main/kotlin/Mago.kt#L214-L237)

---

#### **Criterio global 8: Documentado**
- **(7.h)**: Muestra ejemplos de cómo has documentado y comentado tu código. ¿Que herramientas has utilizado? ¿Cómo aseguras que tu documentación aporte valor para la comprensión, mantenimiento y depuración del código?
- **Respuesta**: He documentado usando Kdoc con la ayuda de inteligencia artificial(Chat GPT) para agilizar el proceso.  Me aseguro de que mi documentacion sea clara y concisa, proporcionando detalles sobre el proposito y el funcionamiento de cada parte del código

---

#### **Criterio global 9: Genéricos**
- **(6.f)**: Muestra ejemplos de tu código sobre cómo has implementado una clase con genéricos. ¿Qué beneficio has obtenido?
- **Respuesta**: He "intentado" usar genericos haciendo una clase inventario para los aventureros que agregen items<T> que sueltan los monstruos al morir
```Kotlin
class Inventario<T> {
    private val items: MutableList<Item<T>> = mutableListOf()

    fun agregarItem(item: Item<T>) {
        items.add(item)
    }

    fun mostrarInventario() {
        println("Inventario:")
        items.forEachIndexed { index, item ->
            println("${index + 1}. ${item.contenido}, Descripcion: ${item.desc}")
        }
    }
}
```
[Link del repo](https://github.com/IES-Rafael-Alberti/prog-practica-libre-trimestre-2-nromragm/blob/089ffe32adedfc637328aedc05c16001e905cbe5/src/main/kotlin/Inventario.kt#L1-L14)