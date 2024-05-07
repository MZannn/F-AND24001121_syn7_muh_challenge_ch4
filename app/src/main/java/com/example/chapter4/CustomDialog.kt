package com.example.chapter4

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.chapter4.databinding.CustomDialogBinding
import com.example.chapter4.model.Notes
import com.example.chapter4.viewModel.NoteViewModel

class CustomDialog(var title: String?, var content: String?, var id: Int?) : DialogFragment() {
    private lateinit var binding: CustomDialogBinding
    private var listener: DialogButtonClickListener? = null
    private val viewModel: NoteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sharedPreferences = SharedPreference
        var userId = sharedPreferences.userId
        binding = CustomDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        if (title != null) {
            binding.etJudul.setText(title)
        }
        if (content != null) {
            binding.etCatatan.setText(content)
        }
        if (id != null) {
            binding.btnInput.text = "Edit"
            binding.btnInput.setOnClickListener {
                updateNote(id!!)
                dismiss()
            }
        } else {
            binding.btnInput.setOnClickListener {
                addNote(userId)
                dismiss()
            }
        }
        builder.setView(binding.root)

        return builder.create()
    }

    fun setDialogButtonClickListener(listener: DialogButtonClickListener) {
        this.listener = listener
    }

    private fun addNote(userId: Int) {
        val title = binding.etJudul.text.toString()
        val content = binding.etCatatan.text.toString()
        var note = Notes(title = title, content = content, userId = userId)
        viewModel.insert(note)
    }

    private fun updateNote(id: Int) {
        val title = binding.etJudul.text.toString()
        val content = binding.etCatatan.text.toString()
        var note = Notes(title = title, content = content, userId = id)
        viewModel.update(note.title, note.content, id)
    }

    override fun onStart() {
        super.onStart()
        // Implementasi logika untuk memanggil callback saat dialog ditampilkan
        listener?.onButtonClicked(isFabClicked = false) // Default, asumsi dialog tidak dipanggil oleh FAB
    }

    interface DialogButtonClickListener {
        fun onButtonClicked(isFabClicked: Boolean)
    }
}