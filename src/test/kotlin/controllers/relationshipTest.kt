/*package controllers

import models.Course
import models.Student
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File

class relationshipTest {

    private var firstYearStudent: Student? = null
    private var secondYearStudent: Student? = null
    private var firstCourse: Course? = null
    private var secondCourse: Course? = null
    private var filledCourse: courseAPI? = courseAPI(JSONSerializer(File("courses.json")))
    private var noCourse: courseAPI? = courseAPI(JSONSerializer(File("no-courses.json")))
    private val serializer1 = JSONSerializer(File("students.json"))
    private val serializer2 = JSONSerializer(File("clear-students.json"))
    private val serializer3 = JSONSerializer(File("course.json"))
    private val courseAPI = courseAPI(serializer3)
    private var filledStudent: studentAPI? = studentAPI(serializer1, courseAPI)
    private var noStudents: studentAPI? = studentAPI(serializer2, courseAPI)

    @BeforeEach
    fun setup() {

        firstYearStudent = Student(1, "John", "Doe", "01/02/2002", true, 26.00, 12)
        secondYearStudent = Student(2, "Jake", "Dune", "24/03/2001", true, 26.50, 13)
        firstCourse = Course(1, "Pharmacy", "Open", 'E')
        secondCourse = Course(2, "Dentistry", "Open", 'I')

        filledStudent!!.add(firstYearStudent!!)
        filledStudent!!.add(secondYearStudent!!)
        filledCourse!!.add(firstCourse!!)
        filledCourse!!.add(secondCourse!!)
    }

    @AfterEach
    fun tearDown() {

        firstYearStudent = null
        secondYearStudent = null
        firstCourse = null
        secondCourse = null
        filledStudent = null
        noStudents = null
        filledCourse = null
        noCourse = null
    }

    @Nested
    inner class AddStudentToCourse {

    @Test
    fun `adding a student to a course`() {
       assertEquals(2, filledStudent!!.numberOfStudents())
       val result = filledStudent!!.addStudentToCourse(1, 1)
        val updatedStudent = filledStudent!!.listStudentByNumber(1)
        assertEquals("Student with Number 1 has been enrolled into the Course: 1", result)
        assertTrue(updatedStudent.contains("courseId=1"))
    }
    }
} */