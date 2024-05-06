package com.example.chapter4.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.chapter4.ViewModelFactory
import com.example.chapter4.databinding.FragmentLoginBinding
import com.example.chapter4.model.User
import com.example.chapter4.viewModel.AuthViewModel
import com.example.simpleviewmodelapp.SharedPreference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        Log.e("SIMPLE_SHARED_PREF", SharedPreference.isLogin.toString())
        viewModel.getAllUsers().observe(viewLifecycleOwner) {
            Log.d("LoginFragment", "onCreateView: $it")
        }
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        val navController = findNavController()
        binding.tvNotHaveAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController.navigate(action)
        }
        binding.btnLogin.setOnClickListener {

            if (binding.etEmail.text.toString()
                    .isNotEmpty() && binding.etPassword.text.toString().isNotEmpty()
            ) {
                var checkLogin: User =
                    login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                if (checkLogin != null) {
                    var bundle = Bundle()
                    bundle.putSerializable("user", "$checkLogin")
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    var intent = Intent(requireContext(), HomeFragment::class.java)
                    intent.putExtra("user", bundle)
                    Log.d("LoginFragment", "onCreateView: $checkLogin")

                    navController.navigate(
                        action,
                        NavOptions.Builder().setPopUpTo(binding.root.id, true, saveState = true)
                            .build()
                    )
                } else {
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }


        }
        return binding.root
    }

    private fun login(email: String, password: String): User {
        return viewModel.login(email, password)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}