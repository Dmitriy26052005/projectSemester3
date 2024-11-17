package controllers

import models.Student
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertFalse

class studentAPITest {
    private var firstYearStudent: Student? = null
    private var secondYearStudent: Student? = null
    private var thirdYearStudent: Student? = null
    private var fourthYearStudent: Student? = null
    private var mastersStudent: Student? = null
    private var filledStudent: studentAPI? = studentAPI(JSONSerializer(File("students.json")))
    private var noStudents: studentAPI? = studentAPI(JSONSerializer(File("clear-students.json")))

    @BeforeEach
    fun setup() {
        firstYearStudent = Student(1, "John", "Doe", "01/02/2002", true, false, 26.00)
        secondYearStudent = Student(2, "Jake", "Dune", "24/03/2001", true, false, 26.50)
        thirdYearStudent = Student(3, "Jacob", "Dan", "31/05/2000", true, false, 25.00)
        fourthYearStudent = Student(4, "Joanne", "Dooly", "03/11/2004", false, true, 30.00)
        mastersStudent = Student(5, "Jett", "Dett", "05/08/2003", false, true, 35.00)

        filledStudent!!.add(firstYearStudent!!)
        filledStudent!!.add(secondYearStudent!!)
        filledStudent!!.add(thirdYearStudent!!)
        filledStudent!!.add(fourthYearStudent!!)
        filledStudent!!.add(mastersStudent!!)
    }

    @AfterEach
    fun tearDown() {
        firstYearStudent = null
        secondYearStudent = null
        thirdYearStudent = null
        fourthYearStudent = null
        mastersStudent = null
        filledStudent = null
        noStudents = null
    }

    @Nested
    inner class AddStudents {
        @Test
        fun `adding a student to a filled list of students, to an ArrayList`() {
            val newStudent = Student(10, "Jason", "Jr", "01/05/2024", false, false, 24.00)
            assertEquals(5, filledStudent!!.numberOfStudents())
            assertTrue(filledStudent!!.add(newStudent))
            assertEquals(6, filledStudent!!.numberOfStudents())
            assertEquals(newStudent, filledStudent!!.findStudent(filledStudent!!.numberOfStudents() - 1))
        }

        @Test
        fun `adding a Student to a clear list of students, adds to an ArrayList`() {
            val newStudent = Student(10, "Jason", "Jr", "01/05/2024", false, false, 24.00)
            assertEquals(0, noStudents!!.numberOfStudents())
            assertTrue(noStudents!!.add(newStudent))
            assertEquals(1, noStudents!!.numberOfStudents())
            assertEquals(newStudent, noStudents!!.findStudent(noStudents!!.numberOfStudents() - 1))
        }
    }

    @Nested
    inner class listStudents {
        @Test
        fun `listAllStudents returns No Students stored message when ArrayList is empty`() {
            assertEquals(0, noStudents!!.numberOfStudents())
            assertTrue(noStudents!!.listAllStudents().lowercase().contains("no students"))
        }

        @Test
        fun `listAllStudents returns Students when ArrayList has students stored`() {
            assertEquals(5, filledStudent!!.numberOfStudents())
            val studentString = filledStudent!!.listAllStudents().lowercase()
            assertTrue(studentString.contains("john"))
            assertTrue(studentString.contains("jake"))
            assertTrue(studentString.contains("jacob"))
            assertTrue(studentString.contains("joanne"))
            assertTrue(studentString.contains("jett"))
        }
    }

    @Nested
    inner class listEnrolledStudents{
        @Test
        fun `listEnrolledStudents returns no enrolled students when ArrayList is empty`() {
            assertEquals(0, noStudents!!.numberOfEnrolledStudents())
            assertTrue(noStudents!!.listEnrolledStudents().lowercase().contains("no enrolled students"))
        }

        @Test
        fun `listEnrolledStudents returns enrolled students when ArrayList has enrolled students stored`() {
            assertEquals(3, filledStudent!!.numberOfEnrolledStudents())
            val enrolledStudentString = filledStudent!!.listEnrolledStudents().lowercase()
            assertTrue(enrolledStudentString.contains("john"))
            assertTrue(enrolledStudentString.contains("jake"))
            assertTrue(enrolledStudentString.contains("jacob"))
            assertFalse(enrolledStudentString.contains("joanne"))
            assertFalse(enrolledStudentString.contains("jett"))
        }

        @Test
        fun `listNotEnrolledStudents returns no enrolled students when ArrayList is empty`() {
            assertEquals(0, noStudents!!.numberOfNotEnrolledStudents())
            assertTrue(noStudents!!.listNotEnrolledStudents().contains("Every student is enrolled in the system"))
        }
    }
        @Test
        fun `listNotEnrolledStudents returns disenrolled students when ArrayList has disenrolled students stored`(){
            assertEquals(2, filledStudent!!.numberOfNotEnrolledStudents())
            val notEnrolledStudentString = filledStudent!!.listNotEnrolledStudents().lowercase()
            assertFalse(notEnrolledStudentString.contains("john"))
            assertFalse(notEnrolledStudentString.contains("jake"))
            assertFalse(notEnrolledStudentString.contains("jacob"))
            assertTrue(notEnrolledStudentString.contains("joanne"))
            assertTrue(notEnrolledStudentString.contains("jett"))
        }

    @Nested
    inner class listStudentByName{
        @Test
        fun `listStudentsByName returns no students when an ArrayList is empty`() {
            assertEquals(0, noStudents!!.numberOfStudents())
            assertTrue(noStudents!!.listStudentByName("jake").contains("No students of this Name"))
        }

        @Test
        fun `listStudentsByName returns no students when the ArrayList doesn't contain equal names`() {
            assertEquals(5, filledStudent!!.numberOfStudents())
            val nameString = filledStudent!!.listStudentByName("joel")
            assertTrue(nameString.contains("No students of this Name"))
        }
    }

    @Test
    fun `listStudentsByName returns all students that match the name when students with that name exist`() {
        assertEquals(5, filledStudent!!.numberOfStudents())

        val nameJohnString = filledStudent!!.listStudentByName("john").lowercase()
        assertTrue(nameJohnString.contains("doe"))
        assertTrue(nameJohnString.contains("01/02/2002"))

        val nameJakeString = filledStudent!!.listStudentByName("jake").lowercase()
        assertTrue(nameJakeString.contains("dune"))
        assertTrue(nameJakeString.contains("24/03/2001"))
    }

    @Nested
    inner class updateStudent {

        @Test
        fun `updating a student that doesn't exist returns a false result`() {
            assertFalse(
                filledStudent!!.updateStudent(
                    7,
                    Student(443, "Jamie", "Johnson", "23/04/2001", true, true, 30.00)
                )
            )
            assertFalse(
                filledStudent!!.updateStudent(
                    -1,
                    Student(222, "Jordan", "Peele", "22/0/1/2015", false, true, 20.00)
                )
            )
            assertFalse(noStudents!!.updateStudent(0, Student(10, "JB", "Keene", "14/06/2000", true, false, 30.00)))
        }

        @Test
        fun `updating a student that exists, returns true, and updates`() {

            assertEquals(thirdYearStudent, filledStudent!!.findStudent(2))
            assertEquals(3, filledStudent!!.findStudent(2)!!.studentNo)
            assertEquals("Jacob", filledStudent!!.findStudent(2)!!.firstName)
            assertEquals("Dan", filledStudent!!.findStudent(2)!!.lastName)

            assertTrue(filledStudent!!.updateStudent(3, Student(33, "Jakob", "Daniels", "31/05/1999", true, false, 26.50)))
            assertEquals("Jakob", filledStudent!!.findStudent(3)!!.firstName)
            assertEquals(true, filledStudent!!.findStudent(3)!!.isEnrolled)
            assertEquals(26.50, filledStudent!!.findStudent(3)!!.courseHours)
        }
    }

    @Nested
    inner class deleteStudent

    @Test
    fun `deleting a student that doesn't exist, returns null`() {
        assertNull(noStudents!!.deleteStudent(0))
        assertNull(filledStudent!!.deleteStudent(-1))
        assertNull(filledStudent!!.deleteStudent(6))
    }

    @Test
    fun `deleting a student that exists, is deleted`() {
        assertEquals(5, filledStudent!!.numberOfStudents())
        assertEquals(secondYearStudent, filledStudent!!.deleteStudent(1))
        assertEquals(4, filledStudent!!.numberOfStudents())
        assertEquals(mastersStudent, filledStudent!!.deleteStudent(3))
        assertEquals(3, filledStudent!!.numberOfStudents())
    }

    @Nested
    inner class PersistenceTests {

        @Test
        fun `savning and loading an empty collection in JSON doesn't crash app`() {
            val savingStudents = studentAPI(JSONSerializer(File("students.json")))
            savingStudents.store()

            val loadedStudents = studentAPI(JSONSerializer(File("students.json")))
            loadedStudents.load()

            assertEquals(0, savingStudents.numberOfStudents())
            assertEquals(0, loadedStudents.numberOfStudents())
            assertEquals(savingStudents.numberOfStudents(), loadedStudents.numberOfStudents())
        }

        @Test
        fun `saving and loading a koaded collection in JSON doesnt't lose data`() {
            val savingStudents = studentAPI(JSONSerializer(File("students.json")))
            savingStudents.add(firstYearStudent!!)
            savingStudents.add(secondYearStudent!!)
            savingStudents.add(thirdYearStudent!!)
            savingStudents.add(mastersStudent!!)
            savingStudents.store()

            val loadedStudents = studentAPI(JSONSerializer(File("students.json")))
            loadedStudents.load()

            assertEquals(4, savingStudents.numberOfStudents())
            assertEquals(4, loadedStudents.numberOfStudents())
            assertEquals(savingStudents.numberOfStudents(), loadedStudents.numberOfStudents())
            assertEquals(savingStudents.findStudent(0), loadedStudents.findStudent(0))
            assertEquals(savingStudents.findStudent(1), loadedStudents.findStudent(1))
            assertEquals(savingStudents.findStudent(2), loadedStudents.findStudent(2))
            assertEquals(savingStudents.findStudent(3), loadedStudents.findStudent(3))

        }
    }

    @Nested
    inner class EnrollStudents

    @Test
    fun `enrolling a student thst does not exist returns false`() {
        assertFalse(filledStudent!!.enrollStudent(10))
        assertFalse(filledStudent!!.enrollStudent(-1))
        assertFalse(noStudents!!.enrollStudent(0))
    }
}