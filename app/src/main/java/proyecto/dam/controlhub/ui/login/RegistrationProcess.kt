package proyecto.dam.controlhub.ui.login


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import proyecto.dam.controlhub.application.App.Companion.auth
import proyecto.dam.controlhub.core.ImageUtil
import proyecto.dam.controlhub.databinding.FragmentRegistrationProcessBinding
import proyecto.dam.controlhub.model.data.CompanyData
import proyecto.dam.controlhub.model.data.UserData
import proyecto.dam.controlhub.model.provider.RegisterFirebase
import java.io.File

class RegistrationProcess : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentRegistrationProcessBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: UserData
    private lateinit var company: CompanyData
    private lateinit var reg: RegisterFirebase
    private val storage = Firebase.storage


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
            user.password
        )
            .addOnCompleteListener {


                auth.signInWithEmailAndPassword(
                    user.email,
                    user.password
                ).addOnCompleteListener {

                    user.id = auth.currentUser?.uid.toString()

                    if (it.isSuccessful) {

                        user.company = company.companyName

                        reg = RegisterFirebase()
                        reg.dbConfigurationComp(company)
                        reg.registerUser(user)

                        //TODO Falta comprobar exixtencias redundantes

                        reg.getInitUser(auth.currentUser?.uid.toString())

                        if(user.imageUrl != ""){

                           /* val imageUtil = ImageUtil(user)
                            imageUtil.uploadImage()*/

                           var referText =
                                user.company.replace(" ","_") +
                                        "/" +
                                        "workers_profile"+
                                        "/"+
                                        user.id

                            var storageRef = storage.reference
                            var file = Uri.parse(user.imageUrl)
                            var imageRef = storageRef.child(referText + "/" + file.lastPathSegment)

                            var uploadTask = imageRef.putFile(file)

                            val ref = storageRef.child(referText + "/" + file.lastPathSegment)
                            val urlTask = uploadTask.continueWithTask { task ->
                                if (!task.isSuccessful) {
                                    task.exception?.let {
                                        throw it
                                    }
                                }
                                ref.downloadUrl
                            }.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    user.imageUrl = task.result.toString()
                                } else {

                                }
                            }
                        }


                        Thread.sleep(1000)

                        viewModel.loginButtonPress()


                    }


                }


            }


    }


}