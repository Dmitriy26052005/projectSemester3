package controllers

import models.Course
import persistence.serializer

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

    fun addCourse(course: Course){
        courses.add(course)
    }

    fun getAllCourses(): List<Course> = courses

    fun courseExists(courseId: Int): Course? {
        return courses.find {it -> it.id == courseId}
    }

    fun numberOfCourses() = courses.size


    fun findCourseById(id: Int): Course? {
        return courses.find { it.id == id }
    }

    fun updateCourse(id: Int, newCourse: Course): Boolean {
        val foundCourse = findCourseById(id)

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