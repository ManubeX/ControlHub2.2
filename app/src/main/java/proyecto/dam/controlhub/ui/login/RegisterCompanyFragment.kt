package proyecto.dam.controlhub.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.core.checkFormUtil
import proyecto.dam.controlhub.databinding.FragmentRegisterCompanyBinding
import proyecto.dam.controlhub.model.data.CompanyData
import proyecto.dam.controlhub.model.provider.RegisterFirebase

class RegisterCompanyFragment : Fragment() {

    private var _binding: FragmentRegisterCompanyBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginViewModel by activityViewModels()
    private val reg = RegisterFirebase()
    private var butonPress = true;


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

        binding.etCompanyName.error =
            checkFormUtil.checkTextField(
                binding.etCompanyName.editText?.text.toString(),
                0,
                reg.nameComp
            )
                ?.let { getString(it) }

        binding.etCompanyAdress.error =
            checkFormUtil.checkTextField(binding.etCompanyAdress.editText?.text.toString(), 1)
                ?.let { getString(it) }

        binding.etCompanyPhone.error =
            checkFormUtil.checkTextField(binding.etCompanyPhone.editText?.text.toString(), 2)
                ?.let { getString(it) }


        binding.etCompanyEmail.error =
            checkFormUtil.checkEmailField(binding.etCompanyEmail.editText?.text.toString(), 3)
                ?.let { getString(it) }


        return checkFormUtil.getResult()


    }





}

