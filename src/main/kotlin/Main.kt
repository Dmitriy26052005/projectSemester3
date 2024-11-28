
import controllers.courseAPI
import controllers.studentAPI
import io.github.oshai.kotlinlogging.KotlinLogging
import models.Course
import models.Student
import persistence.JSONSerializer
import utils.readNextChar
import utils.readNextInt
import utils.readNextLine
import java.io.File

private val logger = KotlinLogging.logger{}
//private val noteAPI = NoteAPI(XMLSerializer(File("notes.xml")))
private val courseAPI = courseAPI(JSONSerializer(File("course.json")))
private val studentAPI = studentAPI(JSONSerializer(File("students.json")), courseAPI)
//declaration of global variables which exist in main.


fun main() {
    runMenu()
}

fun mainMenu() : Int {
    print(
        """
            >      Student Menu
            > |  
            > | 1) Add Student to the System
            > | 2) List Student by Enrolled Status
            > | 3) List Student By Name
            > | 4) List Student By Student Number Specified
            > | 5) Enroll an Existing Student into a Course
            > | 6) Disenroll an Enrolled Student from a Course 
            > | 7) Update Student Details by Specifying their Number
            > | 8) Show the total number of students
            > | 9) Delete a Student from the system
            > 
            >       Course Menu
            >  
            > | 10) Add Course to the System
            > | 11) List All Courses
            > | 12) Show Total Number Of Courses
            > | 13) Update a Course Contents
            > 
            > | 14) Save Details
            > | 15) Load Details
            > 
            > | 18) Exit Application
        """.trimMargin(">"))
    return readNextInt("> --->")
}

fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> addStudent()
            2 -> listStudent()
            3 -> listStudentByName()
            4 -> listStudentByNumber()
            5 -> enrollStudent()
            6 -> disenrollStudent()
            7 -> updateStudentDetails()
            8 -> numberOfStudents()
            9 -> deleteStudent()
            10 -> addCourse()
            11 -> listAllCourses()
            12 -> totalNumberOfCourses()
            13 -> updateCourseDetails()
            14 -> save()
            15 -> load()
            16 -> exit()
            else -> """Please enter a valid option:
                     ${option} is invalid.
          """.trimMargin()
        }
    } while (true)
}
fun addStudent() {
    val studentNo = readNextInt("Please enter a Student Number")
    val fName = readNextLine("Please enter the Student's First Name")
    val lName = readNextLine("Please enter the Student's Last Name")
    val dob = readNextLine("Please enter the Student's Date of Birth")
    val isAdded = studentAPI.add(Student(studentNo, fName, lName, dob, false, 0.0, 0))

    if (isAdded) {
        println("Student added to the system!")
    }

    else {
        println("Unsuccessful")
    }
}

fun listStudent() {
    if (studentAPI.numberOfStudents() > 0) {
        val option = readNextInt(
            """
                >
                > 1 -> View All Students
                > 2 -> View Enrolled Students
                > 3 -> View Not Enrolled Students
            """.trimMargin(">"))

        when (option) {
            1 -> listAllStudents();
            2 -> listEnrolledStudents();
            3 -> listNotEnrolledStudents();
            else -> println("$option: is invalid")
        }
    } else {
        println("Invalid option entered - No Students to display")
    }
}

fun listAllStudents() {
println(studentAPI.listAllStudents())
}

fun listEnrolledStudents() {
    println(studentAPI.listEnrolledStudents())
}

fun listNotEnrolledStudents() {
    println(studentAPI.listNotEnrolledStudents())
}
fun listStudentByName() {

    val searchName = readNextLine("Please enter the first name of the student: ")
    val searchResults = studentAPI.listStudentByName(searchName)
    if (searchResults.isEmpty())
    {
        println("No students found")
    } else {
        println(searchResults)
    }
}

fun listStudentByNumber() {

    val searchNumber = readNextInt("Please enter the student number to search: ")
    val searchResults = studentAPI.listStudentByNumber(searchNumber)
    if (searchResults.isEmpty())
    {
        println("No students found with this ID")
    }
    else {
      println(searchResults)
    }
}

fun enrollStudent() {
    listAllStudents()
    if (studentAPI.numberOfStudents() > 0) {

        val indexToEnroll = readNextInt("Enter the index of the student you want to enrol: ")

        if (studentAPI.enrollStudent(indexToEnroll)) {
            println("Student successfully enrolled")
        } else {
            println("Enrollment unsuccessful")
        }
    }
}

fun disenrollStudent() {
    listEnrolledStudents()
    if (studentAPI.numberOfEnrolledStudents() > 0) {

  val indexToDisenroll = readNextInt("Enter the index of the student to disenroll: ")

        if(studentAPI.disenrollStudent(indexToDisenroll)) {
        println("Student successfully disrolled")
        } else{
            println("Disrollment unsuccessful")
        }
    }
}

fun updateStudentDetails() {
listAllStudents()
    if(studentAPI.numberOfStudents() > 0) {
        val indexToUpdate = readNextInt("Enter the index of a student to edit: ")
        if (studentAPI.isValidIndex(indexToUpdate)) {
            val studentNo = readNextInt("Please enter a Student Number")
            val fName = readNextLine("Please enter the Student's First Name")
            val lName = readNextLine("Please enter the Student's Last Name")
            val dob = readNextLine("Please enter the Student's Date of Birth")

            if (studentAPI.updateStudent(indexToUpdate, Student(studentNo, fName, lName, dob, false, 0.0, 0))) {
                println("Update Successfully Executed")
            } else {
                println("Update Failed")
            }
        } else {
            println("There is no student under this index.")
        }
    }
}
fun numberOfStudents() {
"""${studentAPI.numberOfStudents()}
    > Are in the system
""".trimMargin(">")
}

fun deleteStudent() {
listAllStudents()
    if(studentAPI.numberOfStudents() > 0) {
        val indexToDelete = readNextInt("Enter the index of a Student Number")
        val studentToDelete = studentAPI.deleteStudent(indexToDelete)
        if (studentToDelete != null) {
            println("Student successfully deleted from the system. Deleted student: ${studentToDelete.studentNo}")
        } else {
            println("Delete Unsuccessful")
        }
    }
}

fun addCourse() {
    val id = readNextInt("Please enter a Course ID")
    val courseName = readNextLine("Please enter the Course Name")
    val isCourseOpen = readNextLine("Yes or No")
    val languageTaught = readNextChar("E for English  \nI for Irish  \n S for Spanish")
    val isCreated = courseAPI.addCourse(Course(id, courseName, isCourseOpen, languageTaught))

    if (isCreated != null) {
        println("Course successfully added to the system!")
    }

    else {
        println("Unsuccessful")
    }
}

fun totalNumberOfCourses() {
    """${courseAPI.numberOfCourses()}
    > Are in the system
""".trimMargin(">")
}

fun listAllCourses() {
println(courseAPI.getAllCourses())
}

fun updateCourseDetails() {
    listAllCourses()
    if(courseAPI.numberOfCourses() > 0) {
        val indexToUpdate = readNextInt("Enter the index of a Course to edit: ")
        if (courseAPI.isValidIndex(indexToUpdate)) {
            val id = readNextInt("Please enter a Course ID")
            val courseName = readNextLine("Please enter the Name of the Course")
            val isCourseOpen = readNextLine("Please enter the Course Status: \n Open or Closed")
            val languageTaught = readNextChar("Ine what Language will the course be taught in? " +
                    "\nE for English" +
                    "\nI for Irish" +
                    "\n S for Spanish")

            if (courseAPI.updateCourse(indexToUpdate, Course(id, courseName, isCourseOpen, languageTaught))) {
                println("Update Successfully Executed")
            } else {
                println("Update Failed")
            }
        } else {
            println("There is no Course under this index.")
        }
    }
}

fun save() {
try{
    studentAPI.store()
    courseAPI.store()
} catch (e: Exception) {
    System.err.println("Error while saving student: ${e.message}")
}
}

fun load() {
    try{
        studentAPI.load()
        courseAPI.load()
    } catch (e: Exception) {
        System.err.println("Error while saving student: ${e.message}")
    }
}
fun exit() {
    exit()
}