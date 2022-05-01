package proyecto.dam.controlhub.core



import android.util.Patterns.*
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.model.provider.RegisterFirebase


object checkFormUtil {

    var checkFields: MutableList<Boolean> = mutableListOf()
    var checkFieldInDB = ""
    var reg = RegisterFirebase()


    fun checkTextField(text: String, indexElement: Int): Int? {
        if (text.isEmpty()) {
            checkFields.add(indexElement, false)
            return R.string.required
        } else {
            checkFields.add(indexElement, true)
            return null
        }

    }

    fun checkTextField(text: String, indexElement: Int, resulBD: String): Int? {
        checkFieldInDB = reg.nameComp

        if (text.isEmpty()) {
            checkFields.add(indexElement, false)
            return R.string.required
        } else if (reg.nameComp == text) {
            checkFields.add(indexElement, false)
            return R.string.name_company_exist
        } else checkFields.add(indexElement, true)
        return null

    }

    fun checkEmailField(email: String, indexElement: Int): Int? {
        if (email.isEmpty()) {
            checkFields.add(indexElement, false)
            return R.string.required
        } else if (!EMAIL_ADDRESS.matcher(email).matches()) {
            checkFields.add(indexElement, false)
            return R.string.email_is_not_correct
        } else {
            checkFields.add(indexElement, true)
            return null
        }
    }

    fun checkPassword(password: String, indexElement: Int): Int? {
        if (password.isEmpty()) {
            checkFields.add(indexElement, false)
            return R.string.required
        } else if (password.length < 6) {
            checkFields.add(indexElement, false)
            return R.string.caracters_password
        } else {
            checkFields.add(indexElement, true)
            return null
        }
    }

    fun checkConfirmPassword(password1: String, password2: String, indexElement: Int): Int? {
        if (password1 == password2 && password2.isNotEmpty()) {
            checkFields.add(indexElement, true)
            return null
        } else {
            checkFields.add(indexElement, false)
            return R.string.passwords_are_not_the_same
        }
    }

    fun getResult(): Boolean {
        for (check in checkFields) {
            if (!check) {
                return false
            }
        }
        return true
    }


}
