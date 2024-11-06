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
        return if (students.isNotEmpty()) students.joinToString(separator = "\n") { Student ->
            students.indexOf(Student).toString() + ": " + Student.toString()
        }
        else {
            return "No students in the system"
        }
    }

    fun listEnrolledStudents(): String {
        return if (numberOfEnrolledStudents() == 0) "No enrolled students are in the system"
        else students.filter { Student -> !Student.isNotEnrolled }
            .joinToString(separator = "\n") { Student ->
                students.indexOf(Student).toString() + ": " + Student.toString()
            }
    }

    fun listNotEnrolledStudents(): String {
        return if (numberOfNotEnrolledStudents() == 0) "Every student is enrolled in the system"
        else students.filter { Student -> !Student.isEnrolled }
            .joinToString(separator = "\n") { Student ->
                students.indexOf(Student).toString() + ": " + Student.toString()
            }
    }

    fun listStudentByName(searchString: String) =
        students.filter { Student -> Student.firstName.contains(searchString, true) }
            .joinToString(separator = "\n") { Student ->
                students.indexOf(Student).toString() + ": " + Student.toString()
            }

    fun listStudentByNumber(number: Int) = students.filter { Student -> Student.studentNo.equals(number) }
        .joinToString(separator = "\n") { Student ->
            students.indexOf(Student).toString() + ": " + Student.toString()
        }

    fun numberOfEnrolledStudents(): Int? {
        return students.stream().filter { Student: Student -> !Student.isEnrolled }
            .count()
            .toInt()
    }

    fun numberOfNotEnrolledStudents(): Int? {
        return students.stream().filter { Student: Student -> !Student.isNotEnrolled }
            .count()
            .toInt()
    }

    fun updateStudent(indexToUpdate: Int, Student: Student?): Boolean {

        val foundStudent = findStudent(indexToUpdate)

        if ((foundStudent != null) && (Student != null)) {
            foundStudent.studentNo = Student.studentNo
            foundStudent.firstName = Student.firstName
            foundStudent.lastName = Student.lastName
            foundStudent.isEnrolled = Student.isEnrolled
            foundStudent.isNotEnrolled = Student.isNotEnrolled
            foundStudent.courseHours = Student.courseHours
            foundStudent.dateOfBirth = Student.dateOfBirth
            return true
        }
        return false
    }

    fun findStudent(index: Int): Student? {
        return if (isValidListIndex(index, students)) {
            students[index]
        } else null
    }

    fun enrollStudent(indexToEnroll: Int): Boolean {
        if (isValidIndex(indexToEnroll)) {
            val studentToEnroll = students[indexToEnroll]
            if (!studentToEnroll.isEnrolled) {
                studentToEnroll.isEnrolled = true
            }
        }
        return false
    }

    private fun isValidListIndex(index: Int, list: List<Student>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, students);
    }

    fun deleteStudent(indexToDelete: Int): Student? {
        return if (isValidListIndex(indexToDelete, students)) {
            students.removeAt(indexToDelete)
        } else null
    }
}