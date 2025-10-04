//fun main() {
//
//    var repeat: Boolean = false
//    while (repeat == false){
//        println("Ingresa la distancia del conductor")
//        var driverDistance = readln().toDouble()
//
//        println("Ingresa la disponibilidad del conductor en valores de km")
//        println("0. Para no disponible")
//        println("1. Para disponible")
//        var disponibilityValue = readln().toInt()
//        var disponibility: Boolean = determinateDisponibility(disponibilityValue)
//        if (driverDistance <= 0.5 && disponibility == true) {
//            println("Listo para iniciar recorrido")
//            repeat = true
//        }
//        repeat = determinateTravel(driverDistance, disponibility, repeat)
//    }
//}
//
//fun determinateDisponibility(disponibility: Int): Boolean{
//    if (disponibility == 0) {
//        return false
//    } else {
//        return true
//    }
//}
//
//fun determinateTravel(driverDistance: Double, disponibility: Boolean, repeat: Boolean ): Boolean{
//    var repeatVerif = repeat
//    if (driverDistance > 0.5 && disponibility == true) {
//        println("Conductor disponible, pero muy lejos")
//        repeatVerif = false
//    } else if (driverDistance <= 0.5 && disponibility == false) {
//        println("Conductor cercano, pero no disponible")
//        repeatVerif = false
//    } else if (driverDistance > 0.5 && disponibility == false) {
//        println("No hay conductores disponibles")
//        repeatVerif = false
//    }
//    return repeatVerif
//}