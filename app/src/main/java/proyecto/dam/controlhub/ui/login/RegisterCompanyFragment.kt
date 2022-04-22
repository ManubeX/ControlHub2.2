package proyecto.dam.controlhub.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.databinding.FragmentRegisterBinding
import proyecto.dam.controlhub.databinding.FragmentRegisterCompanyBinding
import proyecto.dam.controlhub.model.data.CompanyData

class RegisterCompanyFragment : Fragment() {

    private var _binding: FragmentRegisterCompanyBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* if(viewModel.getCompanyData != null){
            val company = viewModel.getCompanyData.value
            binding.etCompanyName.
        }*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btNextRegA.setOnClickListener {

            if (checkFieldFilling()){
                viewModel.loadCompanyData(createCompany())
                findNavController().navigate(R.id.registerLoginAdminFragment)
            }

        }
        binding.btCancelRegA.setOnClickListener {
            findNavController().navigate(R.id.loginHomeFragment)
        }


    }

    private fun createCompany(): CompanyData {
        return CompanyData(
            binding.etCompanyName.editText?.text.toString(),
            binding.etCompanyAdress.editText?.text.toString(),
            binding.etCompanyPhone.editText?.text.toString(),
            binding.etCompanyEmail.editText?.text.toString(),
            binding.etBusinessSector.editText?.text.toString()
        )

    }

    private fun checkFieldFilling(): Boolean {
        val registerCorrect: MutableList<Boolean> = mutableListOf()
        if (binding.etCompanyName.editText?.text.toString().isNotEmpty()) {
            registerCorrect.add(0, true)
            binding.etCompanyName.error = null
        } else {
            registerCorrect.add(0, false)
            binding.etCompanyName.error = getString(R.string.required)

        }

        if (binding.etCompanyAdress.editText?.text.toString().isNotEmpty()) {
            registerCorrect.add(1, true)
            binding.etCompanyAdress.error = null
        } else {
            registerCorrect.add(1, false)
            binding.etCompanyAdress.error = getString(R.string.required)

        }
        if (binding.etCompanyPhone.editText?.text.toString().isNotEmpty()) {
            registerCorrect.add(2, true)
            binding.etCompanyPhone.error = null
        } else {
            registerCorrect.add(2, false)
            binding.etCompanyPhone.error = getString(R.string.required)

        }
        if (binding.etCompanyEmail.editText?.text.toString().isEmpty()) {
            registerCorrect.add(3, false)
            binding.etCompanyEmail.error = getString(R.string.required)
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etCompanyEmail.editText?.text.toString())
                .matches()
        ) {
            registerCorrect.add(3, false)
            binding.etCompanyEmail.error = getString(R.string.email_is_not_correct)
        } else {
            registerCorrect.add(3, true)
            binding.etCompanyEmail.error = null
        }
        if (binding.etBusinessSector.editText?.text.toString().isNotEmpty()) {
            registerCorrect.add(4, true)
            binding.etBusinessSector.error = null
        } else {
            registerCorrect.add(4, false)
            binding.etBusinessSector.error = getString(R.string.required)}

            for (check in registerCorrect) {
                if (!check) {
                    return false
                }
            }

            return true

        }



}