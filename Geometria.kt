package geometria

import ean.collections.IList
import ean.collections.emptyList
import java.lang.Math.pow
import kotlin.math.atan
import kotlin.math.sqrt


class Punto {
    private var x: Double = 0.0
    private var y: Double = 0.0

    constructor()

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    fun darX() = x
    fun darY() = y
    fun distanciaOrigen(): Double =
            Math.sqrt(x * x + y * y)

    fun distoOrigen(): Double =
            atan(x / y)
}

fun distanciaEntreDosPuntos(p1: Punto, p2: Punto): Double {
    return sqrt(pow(p1.darX() - p2.darX(), 2.0) + pow(p1.darY() - p2.darY(), 2.0))
}

class Linea {
    private var punto1 = Punto()
    private var punto2 = Punto()

    constructor()

    constructor(punto1: Punto, punto2: Punto) {
        this.punto1 = punto1
        this.punto2 = punto2
    }

    fun pendiente() =
            (punto2.darY() - punto1.darY()) / (punto2.darX() - punto1.darX())

    fun longitud() = distanciaEntreDosPuntos(punto1, punto2)
}

class Polilinea {
    private var lineas: IList<Linea> = emptyList()

    constructor()

    fun agregarLinea(l: Linea) {
        lineas.add(l)
    }

    fun tamaño() = lineas.size

    fun longitud(): Double {
        var sum = 0.0

        for (lin in lineas) {
            sum = sum + lin.longitud()
        }

        return sum
    }

    fun lineaMasLarga(): Linea{
        require(lineas.size > 0) // añadir una precondicion
        var lml = lineas.first

        lineas.forEach {
            if (it.longitud() > lml.longitud()){
                lml = it
            }
        }
        return lml
    }

}