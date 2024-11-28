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
}


