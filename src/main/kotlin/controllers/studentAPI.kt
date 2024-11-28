package controllers

import models.Student
import persistence.serializer

class studentAPI(serializerType: serializer, private val courseAPI: courseAPI) {
    var students = ArrayList<Student>()
    private var serializer: serializer = serializerType

    @Throws(Exception::class)
    fun load() {
        students = serializer.read() as ArrayList<Student>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(students)
    }

    fun add(student: Student): Boolean {
        return students.add(student)
    }

    fun listAllStudents(): String =
        if (students.isEmpty()) "No students in the system"
        else students.joinToString(separator = "\n") { Student ->
            students.indexOf(Student).toString() + ": " + Student.toString()

        }

    fun listEnrolledStudents(): String {
        return if (numberOfEnrolledStudents() == 0) "No enrolled students are in the system"
        else students.filter { Student -> Student.isEnrolled }
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

    fun listStudentByName(searchString: String): String {

        val filteredStudents = students.filter { Student -> Student.firstName.contains(searchString, true) }

        return if (filteredStudents.isEmpty()) {
            "No students of this Name"
        } else {
            students.filter { Student -> Student.firstName.contains(searchString, true) }
                .joinToString(separator = "\n") { Student ->
                    students.indexOf(Student).toString() + ": " + Student.toString()
                }
        }
    }

    fun listStudentByNumber(number: Int): String {

        val symmetryStudent = students.filter { it.studentNo == number }

        return if (symmetryStudent.isEmpty()) {
            "No students found with this ID"
        } else {

            students.filter { Student -> Student.studentNo.equals(number) }
                .joinToString(separator = "\n") { Student ->
                    students.indexOf(Student).toString() + ": " + Student.toString()
                }
        }
    }

    fun numberOfEnrolledStudents(): Int = students.count { it.isEnrolled }

    fun numberOfNotEnrolledStudents(): Int = students.count { !it.isEnrolled }

    fun numberOfStudents(): Int {
        return students.size
    }

    fun updateStudent(indexToUpdate: Int, Student: Student?): Boolean {

        val foundStudent = findStudent(indexToUpdate)

        if ((foundStudent != null) && (Student != null)) {
            foundStudent.studentNo = Student.studentNo
            foundStudent.firstName = Student.firstName
            foundStudent.lastName = Student.lastName
            foundStudent.isEnrolled = Student.isEnrolled
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
                return true
            }
        }
        return false
    }

    fun disenrollStudent(indexToDisenroll: Int): Boolean {
        if (isValidIndex(indexToDisenroll)) {
            val studentToDisenroll = students[indexToDisenroll]
            if (studentToDisenroll.isEnrolled) {
                studentToDisenroll.isEnrolled = false
                return true
            }
        }
        return false
    }

    fun getStudentsByCourseId(courseId: Int): List<Student> = students.filter {it.courseId == courseId}

    fun addStudentToCourse(studentNo: Int, courseId: Int): String {
        val student = students.find{it.studentNo == studentNo}
        if (student == null) {
            return "Student with Number ${studentNo} does not exist in the system"
        } else if (courseAPI.courseExists(courseId) != null) {
            return "Course with such an ID doesn't exist"
        } else {
            students[students.indexOf(student)] = student.copy(courseId = courseId)
            return "Student with Number ${studentNo} has been enrolled into the Course: ${courseId}"
        }
    }

    fun deleteStudent(indexToDelete: Int): Student? {
        return if (isValidListIndex(indexToDelete, students)) {
            students.removeAt(indexToDelete)
        } else null
    }

    private fun isValidListIndex(index: Int, list: List<Student>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, students);
    }
}