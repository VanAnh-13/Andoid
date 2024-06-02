package com.example.appdetailfree.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailCost(
    val userId: String,
    val type: String,
    val dateCreate: String,
    val note: String,
    val cash: Double,
    val category: String
) : Parcelable {
    companion object {
        fun DocumentSnapshot.toDetailCost(): DetailCost? {
            return DetailCost(
                userId = getString("userId")!!,
                type = getString("type")!!,
                dateCreate = getString("dateCreate")!!,
                note = getString("note")!!,
                cash = getDouble("cash")!!,
                category = getString("category")!!
            )
        }
    }
}
