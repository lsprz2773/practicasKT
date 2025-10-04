class Person(override val firstName: String) : IPerson { //constructor primario
    //    var firstName: String = firstName
//        get() = field //custom getter (sirve para agregar logica de negocio)
//    var salary: Double = 0.0
//        get() = field - (field * 0.16)
//        set(value) {field = value - (value * 0.16)}
//        //private set
//
//    open fun walk (){
//        println("walking")
//    }
    override fun walk(): String {
        return "Hello"
    }

    override fun sleep(): String {
        return "I am sleeping"
    }

}