package com.example.chapter4

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.chapter4.databinding.CustomDialogBinding

class CustomDialog : DialogFragment() {
    private lateinit var binding: CustomDialogBinding
    private var listener: DialogButtonClickListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CustomDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())

        builder.setView(binding.root)

        return builder.create()
    }

    fun setDialogButtonClickListener(listener: DialogButtonClickListener) {
        this.listener = listener
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