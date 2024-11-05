package persistence

import com.sun.org.apache.xml.internal.serializer.Serializer
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver
import models.student
import models.course
import java.io.File
import java.io.FileReader
import java.io.FileWriter

abstract class JSONSerializer(private val file: File) : Serializer {

    @Throws(Exception::class)
    fun read(): Any {
        val xStream = XStream(JettisonMappedXmlDriver())
        xStream.allowTypes(arrayOf(student::class.java))
        xStream.allowTypes(arrayOf(course::class.java))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject() as Any
        inputStream.close()
        return obj
    }

    @Throws(Exception::class)
    fun write(obj: Any?) {
        val xStream = XStream(JettisonMappedXmlDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }
}