package com.example.chapter4.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter4.CustomDialog
import com.example.chapter4.SharedPreference
import com.example.chapter4.ViewModelFactory
import com.example.chapter4.adapter.NotesAdapter
import com.example.chapter4.databinding.CustomDialogBinding
import com.example.chapter4.databinding.FragmentHomeBinding
import com.example.chapter4.model.Notes
import com.example.chapter4.model.User
import com.example.chapter4.viewModel.AuthViewModel
import com.example.chapter4.viewModel.NoteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding

    private val noteViewModel: NoteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private val authViewModel: AuthViewModel by activityViewModels {
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
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        val recyclerView = binding.rvNotes
        var sharedPreferences = SharedPreference
        var userId = sharedPreferences.userId
        var user = getUser(userId)
        binding.welcomeUsername.text = "Welcome, ${user.username}"
        binding.tvLogout.setOnClickListener {
            sharedPreferences.clear()
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.fabAdd.setOnClickListener(View.OnClickListener {
            val dialog = CustomDialog(null, null, null)
            dialog.show(requireActivity().supportFragmentManager, "CustomDialog")
        })
        dialogBinding.btnInput.setOnClickListener(View.OnClickListener {
            val notes = Notes(
                title = dialogBinding.etJudul.text.toString(),
                content = dialogBinding.etCatatan.text.toString(),
                userId = userId,
            )
            noteViewModel.insert(notes)
        })
        noteViewModel.getAllNotes(userId).observe(viewLifecycleOwner) { notes ->
            val adapter = NotesAdapter(notes, { item ->
                val dialog =
                    CustomDialog(title = item.title, content = item.content, id = item.id!!)
                dialog.show(requireActivity().supportFragmentManager, "CustomDialog")
            }, { item ->
                noteViewModel.delete(item)
            })

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }

        return binding.root
    }

    fun getUser(id: Int): User {
        return authViewModel.getUserById(id)
    }
}