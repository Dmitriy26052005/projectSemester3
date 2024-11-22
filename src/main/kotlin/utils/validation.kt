package utils

import models.Course
import models.Student

fun formatListString(courseToFormat: List<Course>): String =
    courseToFormat
        .joinToString("\n") {course -> "$course"}

fun formatSetString(studentsToFormat: Set<Student>): String =
    studentsToFormat
        .joinToString("\n") {student -> "$student"}