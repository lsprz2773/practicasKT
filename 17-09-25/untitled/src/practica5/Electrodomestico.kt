package practica5

open class Electrodomestico(
    price: Double,
    color: String,
    elecCons: Char,
    weight: Double
) {
    var price: Double = price
    var color: String = color
    var elecCons: Char = elecCons
    var weight: Double = weight

    companion object{
        const val BASE_PRICE = 100.0
        const val COLOR = "white"
        const val ELEC_CONS = 'F'
        const val WEIGHT = 5.0
    }

    constructor() :
        this(BASE_PRICE,
        COLOR,
        ELEC_CONS,
        WEIGHT)


    constructor(basePrice: Double, weight: Double) :
        this(
            basePrice,
        COLOR,
        ELEC_CONS,
            weight)

//    init {
//        checkElectricalCons()
//    }

    fun checkElectricalCons(elecCons: Char): Char {
        val LETTERS = arrayOf('A', 'B', 'C', 'D', 'E', 'F')
            if (LETTERS.contains(elecCons)){
                return elecCons
            }
        return ELEC_CONS
    }

    fun checkColor(color: String): String {
        val COLORS = arrayOf("white", "black", "red", "blue", "gray")
            if (COLORS.contains(color)){
                return color
            }
        return COLOR
    }

    fun finalPrice(): Double{
        var finalPrice: Double = 0.0
        when(elecCons){
            'A' -> finalPrice =100.0
            'B' -> finalPrice = 80.0
            'C' -> finalPrice = 60.0
            'D' -> finalPrice = 50.0
            'E' -> finalPrice = 30.0
            'F' -> finalPrice = 10.0
        }

        if (weight >= 0 && weight <= 19){
            finalPrice + 10
        } else if (weight >= 20 && weight <= 49){
            finalPrice + 50
        }else if (weight >=50 && weight <= 79){
            finalPrice + 80
        } else if (weight > 80){
            finalPrice + 100
        }

        return finalPrice
    }
}