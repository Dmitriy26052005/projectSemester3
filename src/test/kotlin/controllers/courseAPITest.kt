package controllers

import models.Course
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertFalse

class CourseAPITest {
    private var firstCourse: Course? = null
    private var secondCourse: Course? = null
    private var thirdCourse: Course? = null
    private var fourthCourse: Course? = null
    private var fifthCourse: Course? = null
    private var filledCourse: courseAPI? = courseAPI(JSONSerializer(File("courses.json")))
    private var noCourse: courseAPI? = courseAPI(JSONSerializer(File("no-courses.json")))

    @BeforeEach
    fun setup() {
        firstCourse = Course(1, "Pharmacy", "Open", 'E')
        secondCourse = Course(2, "Dentistry", "Open", 'I')
        thirdCourse = Course(3, "Engineering", "Open", 'S')
        fourthCourse = Course(4, "Philosophy", "Closed", 'S')
        fifthCourse = Course(5, "Physics", "Closed", 'E')

        filledCourse!!.add(firstCourse!!)
        filledCourse!!.add(secondCourse!!)
        filledCourse!!.add(thirdCourse!!)
        filledCourse!!.add(fourthCourse!!)
        filledCourse!!.add(fifthCourse!!)

    }

    @AfterEach
    fun tearDown() {
        firstCourse = null
        secondCourse = null
        thirdCourse = null
        fourthCourse = null
        fifthCourse = null
        filledCourse = null
        noCourse = null
    }

    @Nested
    inner class AddCourses {
        @Test
        fun `adding a course to a filled list of courses, to an ArrayList`() {
            val newCourse = Course(10, "Chemistry", "Open", 'E')
            assertEquals(5, filledCourse!!.numberOfCourses())
            assertTrue(filledCourse!!.add(newCourse))
            assertEquals(6, filledCourse!!.numberOfCourses())
            assertEquals(newCourse, filledCourse!!.findCourse(filledCourse!!.numberOfCourses() - 1))
        }

        @Test
        fun `adding a Course to a clear list of courses, adds to an ArrayList`() {
            val newCourse = Course(10, "Chemistry", "Open", 'E')
            assertEquals(0, noCourse!!.numberOfCourses())
            assertTrue(noCourse!!.add(newCourse))
            assertEquals(1, noCourse!!.numberOfCourses())
            assertEquals(newCourse, noCourse!!.findCourse(noCourse!!.numberOfCourses() - 1))
        }
    }

    @Nested
    inner class ListCourses {
        @Test
        fun `listAllCourses returns No Students stored message when ArrayList is empty`() {
            assertEquals(0, noCourse!!.numberOfCourses())
            assertTrue(noCourse!!.listAllCourses().lowercase().contains("no courses"))
        }

        @Test
        fun `listAllStudents returns Students when ArrayList has students stored`() {
            assertEquals(5, filledCourse!!.numberOfCourses())
            val courseString = filledCourse!!.listAllCourses().lowercase()
            assertTrue(courseString.contains("pharmacy"))
            assertTrue(courseString.contains("dentistry"))
            assertTrue(courseString.contains("engineering"))
            assertTrue(courseString.contains("philosophy"))
            assertTrue(courseString.contains("physics"))
        }
    }

    @Nested
    inner class UpdateCourse {

        @Test
        fun `updating a course that doesn't exist returns a false result`() {
            assertFalse(
                filledCourse!!.updateCourse(
                    7,
                    Course(443, "Jamie", "Johnson", 'I')
                )
            )
            assertFalse(
                filledCourse!!.updateCourse(
                    -1,
                    Course(222, "Jordan", "Peele", 'E')
                )
            )
            assertFalse(noCourse!!.updateCourse(0, Course(10, "JB", "Keene", 'S')))
        }

    }

    @Test
    fun `updating a course that exists, returns true, and updates`() {

        assertEquals(thirdCourse, filledCourse!!.findCourse(2))
        assertEquals(3, filledCourse!!.findCourse(2)!!.id)
        assertEquals("Engineering", filledCourse!!.findCourse(2)!!.courseName)

        assertTrue(
            filledCourse!!.updateCourse(
                3,
                Course(33, "Mechanics", "Closed", 'E')
            )
        )
        assertEquals("Mechanics", filledCourse!!.findCourse(3)!!.courseName)
        assertEquals("Closed", filledCourse!!.findCourse(3)!!.isCourseOpen)
        assertEquals('E', filledCourse!!.findCourse(3)!!.languageTaught)
    }
}



