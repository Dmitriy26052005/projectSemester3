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

    }