package com.example.paymentsapp.registrationscreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.paymentsapp.paymentsscreen.PaymentsFragment
import com.example.paymentsapp.R
import com.example.paymentsapp.databinding.FragmentRegistractionBinding
import org.koin.android.ext.android.inject


class RegistrationFragment : Fragment(R.layout.fragment_registraction), RegistrationView {

    private lateinit var viewBinding: FragmentRegistractionBinding
    private val registrationPresenter: RegistrationPresenter by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentRegistractionBinding.bind(view)
        registrationPresenter.initView(this)
        registrationPresenter.fragmentCreated()

        viewBinding.btnInPut.setOnClickListener {
            registrationPresenter.onClickedBtnInPut(
                login = viewBinding.etLogin.text.toString(),
                password = viewBinding.etPassword.text.toString()
            )
        }
    }

    override fun showErrorIncorrectLoginPassword() {
        Toast.makeText(
            this.context,
            getString(R.string.msg_error_login_password),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showErrorNetwork() {
        Toast.makeText(
            this.context,
            getString(R.string.msg_error_network),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun goToFragmentPayments() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, PaymentsFragment())
            ?.commit()
    }
}
