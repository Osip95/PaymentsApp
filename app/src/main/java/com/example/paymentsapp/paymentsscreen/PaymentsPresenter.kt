package com.example.paymentsapp.paymentsscreen

import com.example.paymentsapp.network.Payment
import com.example.paymentsapp.data.Repository


class PaymentsPresenter(private val repository: Repository) {

    private var paymentsView: PaymentsView? = null

    fun initView(paymentsView: PaymentsView) {
        this.paymentsView = paymentsView
    }
    private val callbackSuccess: (payments: List<Payment>) -> Unit = {
        paymentsView?.setDataRv(payments = it)
    }
    private val callbackErrorNetwork: () -> Unit = {
        paymentsView?.showErrorNetwork()
    }
    fun fragmentCreated() {
        repository.getPaymentsFromServer(callbackSuccess, callbackErrorNetwork)
    }
    fun menuItemPressedExit() {
        repository.resetToken()
        paymentsView?.goToRegistrationFragment()
    }
}