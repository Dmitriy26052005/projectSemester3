package controllers

import models.Student
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistence.XMLSerializer
import java.io.File

class studentAPITest {
    private var firstYearStudent: Student? = null
    private var secondYearStudent: Student? = null
    private var thirdYearStudent: Student? = null
    private var fourthYearStudent: Student? = null
    private var mastersStudent: Student? = null
    private var filledStudent: studentAPI? = studentAPI(XMLSerializer(File("notes.xml")))
    private var noStudents: studentAPI? = studentAPI(XMLSerializer(File("empty-notes.xml")))

@BeforeEach
fun setup(){
    firstYearStudent = Student(1, "John", "Doe", "01//02/2002", false, false, 26.00)
    secondYearStudent = Student(2, "Jake", "Dune", "24//03/2001", false, false, 26.50)
    thirdYearStudent = Student(3, "Jacob", "Dan", "31//05/2000", false, false, 25.00)
    fourthYearStudent = Student(4, "Joanne", "Dooly", "03//11/2004", false, false, 30.00)
    mastersStudent = Student(5, "Jett", "Dett", "05/08/2003", false, false, 35.00)

    filledStudent!!.add(firstYearStudent!!)
    filledStudent!!.add(secondYearStudent!!)
    filledStudent!!.add(thirdYearStudent!!)
    filledStudent!!.add(fourthYearStudent!!)
    filledStudent!!.add(mastersStudent!!)

    @AfterEach
    fun tearDown() {
    firstYearStudent = null
    secondYearStudent = null
    thirdYearStudent = null
    fourthYearStudent = null
    mastersStudent = null
    filledStudent = null
    noStudents = null}

    @Test
    fun `adding a student to a filled list of students, to an ArrayList`(){
        val newStudent = Student(10, "Jason", "Jr", "01/05/2024", false, false, 24.00)
        assertEquals(5, filledStudent!!.numberOfStudents())
        assertTrue(filledStudent!!.add(newStudent))
        assertEquals(6, filledStudent!!.numberOfStudents())
        assertEquals(newStudent, filledStudent!!.findStudent(filledStudent!!.numberOfStudents() - 1))
    }

    @Test
    fun `adding a Student to a clear list of students, adds to an ArrayList`(){
        val newStudent = Student(10, "Jason", "Jr", "01/05/2024", false, false, 24.00)
        assertEquals(0, noStudents!!.numberOfStudents())
        assertTrue(noStudents!!.add(newStudent))
        assertEquals(1, noStudents!!.numberOfStudents())
        assertEquals(newStudent, noStudents!!.findStudent(noStudents!!.numberOfStudents() - 1))
    }

    @Test
    fun `listAllStudents returns No Students stored message when ArrayList is empty`(){}
    assertEquals(0, noStudents!!.numberOfStudents())
    assertTrue(noStudents!!.listAllStudents().lowercase().contains("no students"))
}
}