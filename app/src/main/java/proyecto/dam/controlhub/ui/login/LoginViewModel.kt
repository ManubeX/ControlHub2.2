package proyecto.dam.controlhub.ui.login


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyecto.dam.controlhub.model.data.CompanyData
import proyecto.dam.controlhub.model.data.UserData

class LoginViewModel : ViewModel() {

    private val buttonLogin = MutableLiveData<Boolean>()
     val loginButtonPress : LiveData<Boolean> get() = buttonLogin

    val userError : LiveData<Boolean> get() = buttonLogin

    fun loginButtonPress() {
        buttonLogin.postValue(true)

    }


    private val companyData = MutableLiveData<CompanyData>()
    val getCompanyData : LiveData<CompanyData> get()= companyData

    fun loadCompanyData(data : CompanyData){
        companyData.postValue(data)

    }

    private val userData = MutableLiveData<UserData>()
    val getUserData : LiveData<UserData> get()= userData

    fun loadUserData(data : UserData){
        userData.postValue(data)

    }


}
