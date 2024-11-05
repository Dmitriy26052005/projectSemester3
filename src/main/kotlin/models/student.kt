package models

data class student( var studentNo: Int,
                    var  firstName: String,
                    var lastName: String,
                    var dateOfBirth: String,
                    var isEnrolled: Boolean,
                    var isNotEnrolled: Boolean,
                    var courseHours: Double){

}