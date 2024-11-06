
import controllers.studentAPI
import controllers.courseAPI
import io.github.oshai.kotlinlogging.KotlinLogging
import persistence.JSONSerializer
import persistence.XMLSerializer
import utils.*
import java.io.File

private val logger = KotlinLogging.logger{}
//private val noteAPI = NoteAPI(XMLSerializer(File("notes.xml")))
private val noteAPI = studentAPI(JSONSerializer(File("student.json")))
//declaration of global variables which exist in main.

fun main() {
    runMenu()
}

fun mainMenu() : Int {
    print(
        """
            >      Student Menu
            > | 
            > | 1) Add a Student
            > | 2) List all Students
            > | 3) List Students by Name
            > | 4) List Students by Student Number
            > | 5) Update Student Details
            > | 6) Total Number of Students 
            > | 7) Delete a Student
            >
            >        Course Menu
            > | 8) Create a course
            > | 9) Enroll a student into a course
            > | 10) List all open courses
            > | 11) List course by ID
            > | 12) Update course details
            > | 13) Close a course
            >
            > | 14) Save Details
            > | 15) Load Details
            > 
            > | 16) Exit Application
        """.trimMargin(">"))
    return readNextInt("> --->")
}

fun runMenu() {
    do {
        val option = readNextInt(">--->")
        when (option) {
          1 -> addStudent()
          2 -> listAllStudents()
          3 -> listStudentByName()
          4 -> listStudentbyNumber()
          5 -> updateStudentDetails()
          6 -> numberOfStudents()
          7 -> deleteStudent()
          8 -> addCourse()
          9 -> addStudentToCourse()
          10 -> listAllCourses()
          11 -> listCourseById()
          12 -> updateCourseDetails()
          13 -> closeCourse()
          14 -> save()
          15 -> load()
          16 -> exit()
          else -> """Please enter a valid option:
                     ${option} is invalid.
          """.trimMargin()
        }
    } while(true)
}

fun addStudent() {

}

fun listAllStudents() {

}
fun listStudentByName() {

}

fun listStudentbyNumber() {

}

fun updateStudentDetails() {

}
fun numberOfStudents() {

}

fun deleteStudent() {

}

fun addCourse() {

}

fun addStudentToCourse() {

}

fun listAllCourses() {

}

fun listCourseById() {

}

fun updateCourseDetails() {

}

fun closeCourse() {

}

fun save() {

}

fun load() {

}

fun exit() {

}