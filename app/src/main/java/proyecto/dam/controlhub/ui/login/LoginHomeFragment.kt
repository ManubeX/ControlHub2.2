package proyecto.dam.controlhub.ui.login

import android.os.Bundle
import android.util.Log
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
import proyecto.dam.controlhub.model.provider.RegisterFirebase


class LoginHomeFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentLoginHomeBinding? = null
    private val binding get() = _binding!!
    private val reg = RegisterFirebase()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSingIn.setOnClickListener {

            if (checkLogin()) {
                binding.loadLayout.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(
                    binding.eTEmail.editText?.text.toString().trim(),
                    binding.eTPassword.editText?.text.toString().trim()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (binding.cBRememberMe.isChecked) {
                            App.prefs.saveEmail(
                                binding.eTEmail.editText?.text.toString().trim(),
                                binding.eTPassword.editText?.text.toString().trim()
                            )
                        }
                        reg.getInitUser(auth.currentUser?.uid.toString())
                        Thread.sleep(1000)
                        Log.v("User", App.userAPP?.name ?: "null")

                        viewModel.loginButtonPress()
                    } else {
                        binding.loadLayout.visibility = View.GONE
                        binding.eTEmail.error = getString(R.string.error)
                        binding.eTPassword.error = getString(R.string.error)
                        binding.tVErrorLogin.visibility = View.VISIBLE
                    }
                }
            }

            Log.i(
                binding.eTEmail.editText?.text.toString().trim(),
                binding.eTPassword.editText?.text.toString().trim()
            )
        }

        binding.btRegistrer.setOnClickListener {

            findNavController().navigate(R.id.registerFragment)
        }

    }

    private fun checkLogin(): Boolean {
        var email = false
        var password = false

        if (binding.eTEmail.editText?.text.toString().isEmpty()) {
            email = false
            binding.eTEmail.error = getString(R.string.required)
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.eTEmail.editText?.text.toString())
                .matches()
        ) {
            email = false
            binding.eTEmail.error = getString(R.string.email_is_not_correct)
        } else {
            email = true
            binding.eTEmail.error = null
        }
        if (binding.eTPassword.editText?.text.toString().length < 6) {
            password = false
            binding.eTPassword.error = getString(R.string.caracters_password)
        } else if (binding.eTPassword.editText?.text.toString().isEmpty()) {
            password = false
            binding.eTPassword.error = getString(R.string.required)
        } else {
            password = true
            binding.eTPassword.error = null
        }

        return (email && password)
    }


}


