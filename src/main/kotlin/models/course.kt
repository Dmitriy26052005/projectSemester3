package models

data class course(var courseId: Int,
             var amountOfStudents: Int,
             var isCourseOpen: Boolean,
             var isCourseClosed: Boolean,
             var languageTaught: Char) {}