package proyecto.dam.controlhub.ui.login

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.databinding.FragmentRegisterLoginAdminBinding
import proyecto.dam.controlhub.model.data.UserData


class RegisterAdminLoginFragment : Fragment() {

    private var _binding: FragmentRegisterLoginAdminBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by activityViewModels()
    private var uriImage = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterLoginAdminBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btLoadImage.setOnClickListener {
            loadImage()
        }


        binding.btRegistrerRegA.setOnClickListener {
            if(checkFieldFilling()){

                viewModel.loadUserData(createUserAdmin())
                findNavController().navigate(R.id.registrationProcess)

            }
        }

        binding.btCancelRegA.setOnClickListener {
            findNavController().navigate(R.id.registerCompanyFragment)
        }
    }

    private fun loadImage() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, 2)
    }

    private fun checkFieldFilling() : Boolean {

        binding.verificationAdmin.visibility = View.VISIBLE

        val registerCorrect: MutableList<Boolean> = mutableListOf()
        if (binding.outlinedName.editText?.text.toString().isNotEmpty()) {
            registerCorrect.add(0, true)
            binding.outlinedName.error = null
        } else {
            registerCorrect.add(0, false)
            binding.outlinedName.error = getString(R.string.required)

        }
        if (binding.outlinedFirstLastName.editText?.text.toString().isNotEmpty()) {
            registerCorrect.add(1, true)
            binding.outlinedFirstLastName.error = null
        } else {
            registerCorrect.add(1, false)
            binding.outlinedFirstLastName.error = getString(R.string.required)

        }
        if (binding.outlinedSecondLastName.editText?.text.toString().isNotEmpty()) {
            registerCorrect.add(2, true)
            binding.outlinedSecondLastName.error = null
        } else {
            registerCorrect.add(2, false)
            binding.outlinedSecondLastName.error = getString(R.string.required)

        }
        if (binding.outlinedEmail.editText?.text.toString().isEmpty()){
            registerCorrect.add(3, false)
            binding.outlinedEmail.error = getString(R.string.required)
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.outlinedEmail.editText?.text.toString()).matches()){
            registerCorrect.add(3, false)
            binding.outlinedEmail.error = getString(R.string.email_is_not_correct)
        }else {
            registerCorrect.add(3, true)
            binding.outlinedEmail.error = null
        }

         if (binding.outlinedPassword.editText?.text.toString().isEmpty()) {
            registerCorrect.add(4, false)
            binding.outlinedPassword.error = getString(R.string.required)}
            else if (binding.outlinedPassword.editText?.text.toString().length < 6) {
            registerCorrect.add(5, false)
            binding.outlinedPassword.error = getString(R.string.caracters_password)}
         else {
            registerCorrect.add(4, true)
            binding.outlinedPassword.error = null
        }

        if (binding.outlinedConfirmPassword.editText?.text.toString().length < 6) {
            registerCorrect.add(5, false)
            binding.outlinedConfirmPassword.error = getString(R.string.caracters_password)
        } else if (binding .outlinedConfirmPassword.editText?.text.toString().isEmpty()) {
            registerCorrect.add(5, false)
            binding.outlinedConfirmPassword.error = getString(R.string.required)
        } else if (binding.outlinedConfirmPassword.editText?.text.toString() !=binding.outlinedPassword.editText?.text.toString()){
            registerCorrect.add(5, false)
            binding.outlinedConfirmPassword.error = getString(R.string.passwords_are_not_the_same)
        } else {
            registerCorrect.add(5, true)
            binding.outlinedConfirmPassword.error = null
        }


        binding.verificationAdmin.visibility = View.GONE

        for (check in registerCorrect){
            if(!check){
                return false
            }
        }

        return true

    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(getString(R.string.error))
        builder.setMessage(getString(R.string.message_error_register))
        builder.setPositiveButton(R.string.accept, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun createUserAdmin(): UserData {
        val user = UserData(binding.outlinedName.editText?.text.toString().trim(),
            binding.outlinedFirstLastName.editText?.text.toString().trim(),
            binding.outlinedSecondLastName.editText?.text.toString().trim(),
            binding.outlinedEmail.editText?.text.toString().trim(),
            binding.outlinedPassword.editText?.text.toString().trim())
        user.job = "Admin"
        user.imageUrl = uriImage

        return user

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if(requestCode == 2){
                if (data != null) {
                    binding.imageUser.setImageURI(data.data)
                    var uri = data.data
                    uriImage = uri.toString()
                }
            }
        }
    }
    

}







