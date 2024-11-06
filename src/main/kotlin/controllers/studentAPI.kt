package controllers

import models.Student
import persistence.serializer
import utils.*

class studentAPI(serializerType: serializer) {
    private var students = ArrayList<Student>()
    private var serializer: serializer = serializerType

    @Throws(Exception::class)
    fun load() {
        students = serializer.read() as ArrayList<Student>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(students)
    }

    fun addStudent(student: Student): Boolean {
        return students.add(student)
    }

    fun listAllStudents(): String {
       return if (students.isNotEmpty()) students.joinToString(separator = "\n") { Student -> students.indexOf(Student).toString() + ": " + Student.toString()}
     else {
        return "No students in the system"
    }
        }

    fun listEnrolledStudents(): String {
        if (numberOfEnrolledStudents() == 0) "No enrolled students are in the system"
        else students.filter{Student -> !Student.isNotEnrolled}
            .joinToString(separator = "\n") {Student -> students.indexOf(Student).toString() + ": " + Student.toString()}
    }

    fun listNotEnrolledStudents(): String {
        
    }

    fun listStudentByName(searchString: String) = students.filter {Student -> Student.firstName.contains(searchString, true)}
        .joinToString (separator = "\n") {
            Student -> students.indexOf(Student).toString() + ": " + Student.toString() }

    fun listStudentByNumber(number: Int) = students.filter {Student -> Student.studentNo.equals(number)}
        .joinToString (separator = "\n") {
            Student -> students.indexOf(Student).toString() + ": " + Student.toString()
        }

    fun numberOfEnrolledStudents(): Int? {
        return students.stream().filter{Student: Student -> !Student.isEnrolled}
            .count()
            .toInt()
    }

    fun numberOfNotEnrolledStudents(): Int? {
        return students.stream().filter{Student: Student -> !Student.isNotEnrolled}
            .count()
            .toInt()
    }

    fun updateStudent() {


        }

        fun deleteStudent() {

        }
    }
