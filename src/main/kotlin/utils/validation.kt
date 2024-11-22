package utils

import models.Course
import models.Student

fun formatListString(studentsToFormat: List<Student>): String =
    studentsToFormat
        .joinToString(separator = "\n") { student -> "$student"}.toString()

fun formatSetString(coursesToFormat: List<Course>): String =
    coursesToFormat
        .joinToString(separator = "\n") {undergraduate -> "$undergraduate"}