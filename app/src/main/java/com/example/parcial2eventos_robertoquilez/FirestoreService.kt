package com.example.parcial2eventos_robertoquilez

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService {
    fun obtenerFarmacias(callback: (List<Farmacia>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("farmacias")
            .get()
            .addOnSuccessListener { result ->
                val farmacias = result.map { document ->
                    document.toObject(Farmacia::class.java)
                }
                callback(farmacias)
            }
    }
}