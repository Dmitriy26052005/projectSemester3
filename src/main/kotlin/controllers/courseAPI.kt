package controllers

import models.Course
import persistence.serializer

/**
 * This class manages an ArrayList of course objects, and provides standard Create Read Update Delete functionality,
 * and other useful methods. It uses [Serializer] to load anf save the students persistently.
 *
 * @property serializer A serializer instance for reading and creating the students.
 * @constructor Initializes the studentAPI with the specified [serializerType], which is JSON
 */

class courseAPI (serializerType: serializer) {

    private var courses = mutableListOf<Course>()
    private var serializer: serializer = serializerType

    @Throws(Exception::class)
    fun load() {
        courses = serializer.read() as ArrayList<Course>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(courses)
    }

    fun add(course: Course): Boolean {
       return courses.add(course)
    }

    fun listAllCourses(): String =
        if (courses.isEmpty()) "No courses in the system"
        else courses.joinToString(separator = "\n") { Course ->
            courses.indexOf(Course).toString() + ": " + Course.toString()

        }

    fun courseExists(courseId: Int): Course? {
        return courses.find {it -> it.id == courseId}
    }

    fun numberOfCourses() = courses.size

    fun findCourse(index: Int): Course? {
        return if (isValidListIndex(index, courses)) {
            courses[index]
        } else null
    }


    fun updateCourse(id: Int, newCourse: Course): Boolean {
        val foundCourse = findCourse(id)

        if (foundCourse != null) {
            foundCourse.id = newCourse.id
            foundCourse.courseName = newCourse.courseName
            foundCourse.isCourseOpen = newCourse.isCourseOpen
            foundCourse.languageTaught = newCourse.languageTaught

            return true
        }
        return false
    }
    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, courses)
    }

    fun isValidListIndex(index: Int, list: MutableList<Course>): Boolean {
        return (index >= 0 && index < list.size)
    }
}