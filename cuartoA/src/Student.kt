class Student(var studentName: String): Person(studentName) {
    override fun walk() {
        println("walking as a student")
    }
}