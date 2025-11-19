package com.luisperez.scores_unidad3.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.luisperez.scores_unidad3.domain.model.Student
import kotlin.collections.listOf

class DashboardViewModel: ViewModel() {

    var students by mutableStateOf(listOf(
        Student(1,"Luis","Perez", 4,'A',10.0)
    ))

    fun getStudentById(id: Int): Student?{
        return students.firstOrNull { it.id == id }
    }

    fun addStudent(name: String, lastName: String, grade: Int, group:Char, score: Double){
        val idStudent: Int =students.last().id+1
        val student: Student = Student(idStudent, name, lastName, grade, group, score)
        students = students + student
    }

    fun editStudent(id: Int, name: String, lastName: String, grade: Int, group:Char, score: Double){
        students = students.map { student ->
            if (student.id == id) {
                student.copy(id, name, lastName, grade, group, score)
            } else {
                student
            }
        }
    }

    fun deleteStudent(id:Int){
        students = students.filter { student ->
            student.id != id
        }
    }

    fun getStudentsByGroup(group: Char): List<Student> {
        return students.filter { it.group == group }
    }

    fun getStudentsA(){
        getStudentsByGroup('A')
    }

    fun getStudentsB(){
        getStudentsByGroup('B')
    }

    fun getStudentsC(){
        getStudentsByGroup('C')
    }

}
