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

    /**
     * Add course to the system
     * @param course, the [Course] to be added
     * @return 'true' if successful, 'false' otherwise.
     */

    fun add(course: Course): Boolean {
       return courses.add(course)
    }

    /**
     * list all available courses
     * Expression function which returns course objects.
     */
    fun listAllCourses(): String =
        if (courses.isEmpty()) "No courses in the system"
        else courses.joinToString(separator = "\n") { Course ->
            courses.indexOf(Course).toString() + ": " + Course.toString()

        }

    /**
     * Clarify if the course is present in the system
     * @param courseId, identify the [Course] object by its it
     * @return Course?, the course object is returned
     */
    fun courseExists(courseId: Int): Course? {
        return courses.find {it -> it.id == courseId}
    }

    /**
     * Indicated the number of courses in the system
     */
    fun numberOfCourses() = courses.size

    /**
     * Find a particular course in the system
     * @param index, indicate the course existence by its ID
     */
    fun findCourse(index: Int): Course? {
        return if (isValidListIndex(index, courses)) {
            courses[index]
        } else null
    }

    /**
     * Update the course details
     * @param id, indicate which course to update
     * @param newCourse, shows the updated version of the attribute for the object.
     * @return 'true' if successful, 'false' otherwise
     */
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