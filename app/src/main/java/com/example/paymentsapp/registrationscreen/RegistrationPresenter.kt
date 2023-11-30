package com.example.paymentsapp.registrationscreen

import com.example.paymentsapp.data.Repository


class RegistrationPresenter(private val repository: Repository) {
    private var registrationView: RegistrationView? = null
    private val callbackSuccess: (token: String) -> Unit = {
        if (it == "") {
            registrationView?.showErrorIncorrectLoginPassword()
        } else {
            registrationView?.goToFragmentPayments()
        }
    }
    private val callbackErrorNetwork: () -> Unit = {
        registrationView?.showErrorNetwork()
    }

    fun initView(registrationView: RegistrationView) {
        this.registrationView = registrationView
    }

    fun fragmentCreated() {
        if (repository.getToken() !== "") {
            registrationView?.goToFragmentPayments()
        }
    }

    fun onClickedBtnInPut(login: String, password: String) {
        repository.getTokenFromServer(
            login,
            password,
            callbackSuccess = callbackSuccess,
            callbackErrorNetwork = callbackErrorNetwork
        )
    }
}