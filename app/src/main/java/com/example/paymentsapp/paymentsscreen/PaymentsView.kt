package com.example.paymentsapp.paymentsscreen

import com.example.paymentsapp.network.Payment

interface PaymentsView {
    fun setDataRv(payments: List<Payment>)
    fun goToRegistrationFragment()
    fun showErrorNetwork()
}