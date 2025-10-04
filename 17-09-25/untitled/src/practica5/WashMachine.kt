package practica5

class WashMachine(var charge: Double): Electrodomestico() {
    companion object {
        const val CHARGE = 5.0
    }

    constructor(): this(
        CHARGE
    )
}