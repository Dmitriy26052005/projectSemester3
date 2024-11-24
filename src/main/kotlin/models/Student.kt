package models

data class Student( var studentNo: Int,
                    var firstName: String,
                    var lastName: String,
                    var dateOfBirth: String,
                    var isEnrolled: Boolean = false,
                    var courseHours: Double){}
