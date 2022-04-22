package proyecto.dam.controlhub.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import proyecto.dam.controlhub.databinding.FragmentRegisterQrLoginBinding


class RegisterQrLoginFragment : Fragment() {
    private var _binding: FragmentRegisterQrLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterQrLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


}