package proyecto.dam.controlhub.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import proyecto.dam.controlhub.R
import proyecto.dam.controlhub.application.App
import proyecto.dam.controlhub.application.App.Companion.auth
import proyecto.dam.controlhub.databinding.FragmentLoginHomeBinding
import proyecto.dam.controlhub.databinding.FragmentRegistrationProcessBinding
import proyecto.dam.controlhub.model.data.CompanyData
import proyecto.dam.controlhub.model.data.UserData
import proyecto.dam.controlhub.model.provider.RegisterFirebase

class RegistrationProcess : Fragment() {
    private val viewModel : LoginViewModel by activityViewModels()
    private var _binding: FragmentRegistrationProcessBinding? = null
    private val binding get() = _binding!!
    private lateinit var user : UserData
    private lateinit var company: CompanyData
    private lateinit var reg : RegisterFirebase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationProcessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        company = viewModel.getCompanyData.value!!
        user = viewModel.getUserData.value!!

              auth.createUserWithEmailAndPassword(
                    user.email,
                    user.password)
                    .addOnCompleteListener {


                        auth.signInWithEmailAndPassword(
                            user.email,
                            user.password).addOnCompleteListener{

                            user.id = auth.currentUser?.uid.toString()

                            if(it.isSuccessful){

                                user.company = company.companyName

                                reg = RegisterFirebase()
                                reg.dbConfigurationComp(company)
                                reg.registerUser(user)

                                //TODO Falta comprobar exixtencias redundantes

                                viewModel.loginButtonPress()


                            }


                        }


                    }


}




}