package controllers

import models.Course

class courseAPI {
    private val courses = mutableListOf<Course>()

    fun addCourse(course: Course) {
        //add code for adding a dept with a unique id
        courses.add(course)
    }

    fun getAllCourses(): List<Course> = courses

    fun courseExists(courseId: Int): Course? {
        return courses.find { it -> it.courseId == courseId }
    }
}