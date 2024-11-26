package controllers

import models.Course
import persistence.serializer

class courseAPI (serializerType: serializer) {
    private var courses = ArrayList<Course>()
    private var serializer: serializer = serializerType

    @Throws(Exception::class)
    fun load() {
        courses = serializer.read() as ArrayList<Course>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(courses)
    }

    private var finalId = 0
    private fun getId() = finalId++

    fun addCourse(course: Course): Boolean {
        course.courseId = getId()
        return courses.add(course)
    }

    fun removeCourse(id: Int) = courses.removeIf{ course -> course.courseId == id}

    fun updateCourse(id: Int, course: Course) {
        val
    }

    }