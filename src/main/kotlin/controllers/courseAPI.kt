package controllers

import models.Course
import models.Student
import persistence.serializer

class courseAPI (serializerType: serializer, private val studentAPI: studentAPI) {

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

    fun findStudentInCourse(id: Int): Student? {
        return studentAPI.students.find { student -> student.studentNo == id }
    }

    fun findCourseById(id: Int): Course? {
        return courses.find { it.id == id }
    }

    fun deleteStudentFromCourse(id: Int): Boolean {
        return studentAPI.students.removeIf { student -> student.studentNo == id }
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

}