fun main() {
//    //Arreglos
//    val personArray: Array<String> = arrayOf("Mary", "Bob", "James", "John")
//    println(personArray[2])
//
//    //Listas mutables e inmutables
//        //Mutables
//        val personListMutable: MutableList<String> = mutableListOf("Mary", "Bob", "James", "John")
//        personListMutable.add("Mary")
//        personListMutable[1] = "Pedro"
//        personListMutable.remove("Bob")
//
//        //Inmutables
//        //(::) -> Referencia a métodos
//        val personList: List<String> = listOf("Tony", "Isaac", "Fernando", "Antonio")
//        personList.forEach( ::println )
//
//    //funciones
//    //Los dos puntos después de los parámetros será el tipo de dato que retornará (en el caso de que retorne, Unit es el void)
//    fun sleep(): String{
//        return "I'm sleeping"
//    }
//
//    //En el caso de simplificar el de arriba:
//    fun shortSleep() = "I'm sleeping"
//
//
//    //Funciones lambda (función anónima o función flecha)
//    var plusNumbers = {
//        a: Int, b: Int -> a + b
//    }
//
//    println(plusNumbers(2,3))

//    var person: Person = Student("Luis")
//    println(person.firstName)
//    person.walk()

    var text = "London"
    println(text.doSomething())
}

fun String.doSomething(): String { //función de extensión (permite agregar funciones a la clase sin tener que modificar el archivo de clase)
    return "This is a extension function"
}