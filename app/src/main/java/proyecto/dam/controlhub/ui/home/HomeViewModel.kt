package proyecto.dam.controlhub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyecto.dam.controlhub.core.DataUtil

class HomeViewModel : ViewModel() {
    var dataUtil = DataUtil()



    private val _day = MutableLiveData<Int>().apply {

        value = dataUtil.getDay()
    }
    val day: LiveData<Int> = _day

    private val _month = MutableLiveData<Int>().apply {
        value = dataUtil.getMoth()
    }

    val month: LiveData<Int> = _month

    private val _year = MutableLiveData<Int>().apply {
        value = dataUtil.getYear()
    }
    val year: LiveData<Int> = _year


}