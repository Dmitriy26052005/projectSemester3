package controllers

import models.Student
import persistence.serializer

/**
 * This class manages an ArrayList of student objects, and provides standard Create Read Update Delete functionality,
 * and other useful methods. It uses [Serializer] to load anf save the students persistently.
 *
 * @property serializer A serializer instance for reading and creating the students.
 * @constructor Initializes the studentAPI with the specified [serializerType], which is JSON
 */


class studentAPI(serializerType: serializer, private val courseAPI: courseAPI) {
    var students = ArrayList<Student>()
    private var serializer: serializer = serializerType

    /**
     * Adds a new Student to the ArrayList
     *
     * @param student the [Student] to be added
     * @return 'true' if the student was successfully added, 'false' otherwise.
     */

    fun add(student: Student): Boolean {
        return students.add(student)
    }

    /** Listing Students methods
     * @param students, the students ArrayList is used for operations
     * @return String outputs.
     */
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

    /**
     * Shows the amount of students currently in the system, with specified attribute values
     */
    fun numberOfEnrolledStudents(): Int = students.count { it.isEnrolled }

    fun numberOfNotEnrolledStudents(): Int = students.count { !it.isEnrolled }

    fun numberOfStudents(): Int {
        return students.size
    }

    /**
     * Update the student details
     * @param indexToUpdate, you choose which student to update
     * @return 'true' if the update worked, otherwise 'false'
     */
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

    /**
     * function used in other methods to find students
     * @param index, enter the student to find according to their index
     */
    fun findStudent(index: Int): Student? {
        return if (isValidListIndex(index, students)) {
            students[index]
        } else null
    }

    /**
     * Enroll / Disenroll students from the system
     * @param indexToEnroll, you choose the student to enroll
     * @param indexToDisenroll, you choose the student to dieenroll
     * @return 'true' if the operation was successful, 'false' otherwise
     */
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

    /**
     * Control the relationship between the models
     * @param studentno, you choose the student to add
     * @param courseId, you choose the course to enroll the student into
     * @return Success or Failed message, after the execution of the operation.
     */
    fun addStudentToCourse(studentNo: Int, courseId: Int): String {
        val student = students.find{it.studentNo == studentNo}
        if (student == null) {
            return "Student with Number: ${studentNo} does not exist in the system"
        } else if (courseAPI.courseExists(courseId) == null) {
            return "Course with such an ID: ${courseId} doesn't exist"
        } else {
            students[students.indexOf(student)] = student.copy(courseId = courseId)
            return "Student with Number ${studentNo} has been enrolled into the Course: ${courseId}"
        }
    }

    fun removeStudentFromCourse(studentNo: Int, courseId: Int): String {
        val student = students.find{it.studentNo == studentNo}
        if (student == null) {
            return "Student with such an ID doesn't exist in the system"
        } else if (courseAPI.courseExists(courseId) == null) {
            return "Course with such an ID doesn't exist in the system"
        } else {
            students[students.indexOf(student)] = student.copy(courseId = null)
            return "Student with Number ${studentNo} has been removed from the CourseL ${courseId}"

        }
    }

    /**
     * Delete student from the system
     * @param, choose the student to delete
     * @return message if the operation worked or not.
     */
    fun deleteStudent(indexToDelete: Int): Student? {
        return if (isValidListIndex(indexToDelete, students)) {
            students.removeAt(indexToDelete)
        } else null
    }

    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, students)
    }

    fun isValidListIndex(index: Int, list: List<Student>): Boolean {
        return (index >= 0 && index < list.size)
    }

    @Throws(Exception::class)
    fun load() {
        students = serializer.read() as ArrayList<Student>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(students)
    }

}


