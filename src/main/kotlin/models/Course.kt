package models


data class Course(var courseId: Int,
                  var courseName: String,
             var isCourseOpen: Boolean,
             var languageTaught: Char){}
