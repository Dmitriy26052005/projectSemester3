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
    }