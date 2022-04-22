package proyecto.dam.controlhub.model.provider

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.model.data.CompanyData
import proyecto.dam.controlhub.model.data.UserData
import java.lang.Thread.sleep

class RegisterFirebase (){

    val db = Firebase.firestore
    val COLLECTION_APP = "CONTROLHUB"
    val COLLECTION_USER = "USERS"

    fun registerUser(user: UserData) {
        db.collection(COLLECTION_APP)
            .document(user.company)
            .collection("Employees")
            .document(user.id).set(user)
            .addOnSuccessListener {
            }.addOnFailureListener { e ->
                Log.v("BBDD", "Error", e)
            }

        val collectionUser = hashMapOf(
            "id_User" to user.id,
            "company" to user.company
        )
        db.collection(COLLECTION_USER)
            .document(user.id).set(collectionUser)
    }

    fun dbConfigurationComp(company: CompanyData) {

        db.collection(COLLECTION_APP).document(company.companyName).set(company)
            .addOnSuccessListener {

            }.addOnFailureListener { e ->
                Log.v("DB", "Error", e)
            }

    }


    fun getInitUser(id: String) {
        var company: String
        var idUser: String
        var userReturn = UserData()
        db.collection(COLLECTION_USER).document(id).get()
            .addOnSuccessListener { document ->
                company = (document.get("company") as String?).toString()
                idUser = (document.get("id_User") as String?).toString()
                db.collection(COLLECTION_APP)
                    .document(company)
                    .collection("Employees")
                    .document(idUser).get().addOnSuccessListener {
                       val userRec = it.toObject<UserData>()!!
                        App.userAPP = userRec
                        Log.v("User Rec", userRec.name)
                        Log.v("User Rec", userReturn.name ?:"no")
                    }

            }

    }
}




