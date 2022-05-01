package proyecto.dam.controlhub.model.provider

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.awaitAll
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.core.checkFormUtil
import proyecto.dam.controlhub.model.data.CompanyData
import proyecto.dam.controlhub.model.data.ProductData
import proyecto.dam.controlhub.model.data.UserData
import java.lang.Thread.sleep
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RegisterFirebase() {

    val db = Firebase.firestore
    val COLLECTION_APP = "CONTROLHUB"
    val COLLECTION_USER = "USERS"
    var nameComp ="-1"

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

    fun setProduct(product: ProductData, company: String) {

        db.collection(COLLECTION_APP).document(company).collection("Products")
            .document(product.name).set(product)

    }

    fun deleteProduct(product: ProductData, company: String) {
        db.collection(COLLECTION_APP).document(company).collection("Products")
            .document(product.name).delete()
    }

    fun getProductList(company: String){
        db.collection(COLLECTION_APP).document(company).collection("Products").get()
            .addOnSuccessListener { document ->
               if(document != null){
                   for(product in document){
                       App.listProductsApp.add(product.toObject<ProductData>())
                   }
               }

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
                        Log.v("User Rec", userReturn.name ?: "no")
                    }

            }

    }

    fun checkFieldDB(company: String){

            val task = db.collection(COLLECTION_APP).document(company).get()
                .addOnSuccessListener {
                    Log.v("BD", "Company " + company)
                    val result = (it.get("companyName") as String?).toString()
                    Log.v("BD", "Result " + result)
                    nameComp= result
                }.addOnFailureListener {
                    nameComp="null"
                }
    }


    fun checkFieldDB2(company: String):Boolean {
        var result = ""
        var count = CountDownLatch(1)
            val tk = db.collection(COLLECTION_APP).document(company).get()
                .addOnSuccessListener {
                    Log.v("BD", "Company " + company)
                    result = (it.get("companyName") as String?).toString()
                    Log.v("BD", result)
                    //sleep(500)
                    //checkFormUtil.checkFieldInDB =
                    nameComp= result
                    count.countDown()
//                    Log.v("BD", checkFormUtil.checkFieldInDB)
                }
            Tasks.whenAll(tk)
        if(result == company){
            return false
        }else return true

    }



}





