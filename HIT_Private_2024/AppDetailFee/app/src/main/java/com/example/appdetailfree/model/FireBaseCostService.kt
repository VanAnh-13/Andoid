package com.example.appdetailfree.model

import com.example.appdetailfree.model.DetailCost.Companion.toDetailCost
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

object FireBaseCostService {
    val authentication by lazy { Firebase.auth }
    private val database by lazy { Firebase.firestore }

    suspend fun putData(data: DetailCost): Boolean {
        try {
            database
                .collection("users")
                .document()
                .set(data)
                .await()

            return true
        } catch (_: Exception) {
            return false
        }
    }

    suspend fun getData(): List<DetailCost> = try {
        database
            .collection("users")
            .get()
            .await()
            .documents
            .mapNotNull { documentSnapshot ->
                documentSnapshot.toDetailCost()
            }

    } catch (_: Exception) {
        emptyList()
    }
}