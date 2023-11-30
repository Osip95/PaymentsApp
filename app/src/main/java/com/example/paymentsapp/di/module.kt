package com.example.paymentsapp.di

import android.content.Context
import com.example.paymentsapp.data.APP_PREFERENCES
import com.example.paymentsapp.data.BASE_URL
import com.example.paymentsapp.network.PaymentsApi
import com.example.paymentsapp.paymentsscreen.PaymentsPresenter
import com.example.paymentsapp.registrationscreen.RegistrationPresenter
import com.example.paymentsapp.data.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val paymentsModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<PaymentsApi> {
        get<Retrofit>().create(PaymentsApi::class.java)
    }

    single<Repository> {
        Repository(
            get(),
            androidContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        )
    }
    single { RegistrationPresenter(get()) }

    single { PaymentsPresenter(get()) }

}