package com.example.learncoroutines

import com.example.learncoroutines.api.ApiClientService

class Repository {
    suspend fun getAllStudent() =
        ApiClientService.apiService.getAllStudent()

    suspend fun getStudentById(studentId: String) =
        ApiClientService.apiService.getStudentById(studentId = studentId)

    suspend fun createStudent(student: Account) =
        ApiClientService.apiService.createStudent(student = student)
}