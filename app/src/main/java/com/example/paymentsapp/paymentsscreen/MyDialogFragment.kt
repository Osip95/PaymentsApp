package com.example.paymentsapp.paymentsscreen

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.paymentsapp.R
import org.koin.android.ext.android.inject

class MyDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_title)
                .setMessage(R.string.msg_dialog)
                .setPositiveButton(R.string.positive_btn_dialog) {
                        dialog, _ ->  dialog.cancel()
                    val paymentsPresenter: PaymentsPresenter by inject()
                    paymentsPresenter.menuItemPressedExit()

                }
                .setNegativeButton(R.string.negative_btn_dialog){
                        dialog, _ ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("IllegalStateException")
    }
}