//import kotlin.math.pow
//import kotlin.math.sqrt
//
//class Raices{
//    var a = 0.0
//    var b = 0.0
//    var c = 0.0
//
//    constructor(a: Double, b: Double, c: Double) {
//        this.a = a
//        this.b = b
//        this.c = c
//    }
//
//    fun obtenerRaices(a: Double, b: Double, c: Double): Pair<Double, Double> {
//        val raizUno: Double = (-(b) + (sqrt(2-(4*a*c))))/(2*a)
//        val raizDos: Double = (-(b) + (sqrt(2+(4*a*c))))/(2*a)
//        return Pair(raizUno, raizDos)
//    }
//
//    fun obtenerRaiz(a: Double, b: Double, c: Double): Double {
//        val raiz =  -b / (2*a)
//        return raiz
//    }
//
//    fun getDiscriminante(a: Double, b: Double, c: Double): Double {
//        var discriminante: Double = (b.pow(2.0)-4.0*a*c)
//
//        return discriminante
//    }
//
//    fun tieneRaices(a: Double, b: Double, c: Double):Boolean {
//        return if (getDiscriminante(a, b, c) >= 0.0)
//            true
//        else
//            false
//    }
//
//    fun tieneRaiz(a: Double, b: Double, c: Double): Boolean {
//        return if (getDiscriminante(a, b, c) <= 0.0)
//            true
//        else
//            false
//    }
//
//    fun calcular(a: Double, b: Double, c: Double) {
//        if (tieneRaices(a, b, c) == true){
//            val (raizUno, raizDos) = obtenerRaices(a, b, c)
//            println("Contiene dos raices")
//            println("Raiz uno: $raizUno")
//            println("Raiz dos: $raizDos")
//        } else if (tieneRaiz(a, b, c) == true){
//            val raiz = obtenerRaiz(a, b, c)
//            println("Raiz: $raiz")
//        }
//    }
//}