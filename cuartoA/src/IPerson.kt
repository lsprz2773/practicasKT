interface IPerson {
    val firstName: String
    fun walk(): String
    fun sleep(): String {
        return "I am sleeping"
    }
}