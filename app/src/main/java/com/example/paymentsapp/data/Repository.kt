package com.example.paymentsapp.data


import android.content.SharedPreferences
import com.example.paymentsapp.network.BodyRegistration
import com.example.paymentsapp.network.Payment
import com.example.paymentsapp.network.PaymentsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Repository(private val paymentsApi: PaymentsApi, private val mSetting: SharedPreferences) {

    init {
        loadPreferences()
    }

    private var payments: List<Payment> = ArrayList()
    private lateinit var token: String

    fun getToken(): String {
        return token
    }

    fun resetToken() {
        token = ""
        savePreferences()
    }

    fun getTokenFromServer(
        login: String,
        password: String,
        callbackSuccess: (token: String) -> Unit,
        callbackErrorNetwork: () -> Unit
    ) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                token = paymentsApi.getToken(
                    bodyRegistration = BodyRegistration(
                        login,
                        password
                    )
                ).response?.token ?: ""
                CoroutineScope(Dispatchers.Main).launch {
                    callbackSuccess(token)
                    savePreferences()
                }
            } catch (e: Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    callbackErrorNetwork()
                }
            }
        }
    }

    fun getPaymentsFromServer(
        callbackSuccess: (payments: List<Payment>) -> Unit,
        callbackErrorNetwork: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                payments = paymentsApi.getPayments(token = token).payments
                CoroutineScope(Dispatchers.Main).launch {
                    callbackSuccess(payments)
                }
            } catch (e: Exception) {

                CoroutineScope(Dispatchers.Main).launch {

                    callbackErrorNetwork()
                }
            }
        }
    }

    private fun savePreferences() {
        val editor = mSetting.edit()
        editor.putString(PREFERENCES_TOKEN_KEY, token)
        editor.apply()
    }

    private fun loadPreferences() {
        token = if (mSetting.contains(PREFERENCES_TOKEN_KEY)) {
            mSetting.getString(
                PREFERENCES_TOKEN_KEY,
                ""
            ) ?: ""
        } else {
            ""
        }
    }
}
