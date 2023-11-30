package com.example.paymentsapp.paymentsscreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymentsapp.network.Payment
import com.example.paymentsapp.R
import com.example.paymentsapp.data.TAG_DIALOG_FRAGMENT
import com.example.paymentsapp.databinding.PaymentsFragmentBinding
import com.example.paymentsapp.registrationscreen.RegistrationFragment
import org.koin.android.ext.android.inject

class PaymentsFragment : Fragment(R.layout.payments_fragment), PaymentsView {

    private lateinit var viewBinding: PaymentsFragmentBinding
    private val adapter by lazy { PaymentsAdapter() }
    private val paymentsPresenter: PaymentsPresenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = PaymentsFragmentBinding.bind(view)
        viewBinding.rvPayments.adapter = adapter
        viewBinding.rvPayments.layoutManager = LinearLayoutManager(activity)
        paymentsPresenter.initView(this)
        paymentsPresenter.fragmentCreated()

        viewBinding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.change_user -> {
                    val myDialogFragment = MyDialogFragment()
                    myDialogFragment.show(requireActivity().supportFragmentManager, TAG_DIALOG_FRAGMENT)
                    true
                }
                else -> false
            }
        }
    }

    override fun goToRegistrationFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, RegistrationFragment())
            ?.commit()
    }

    override fun showErrorNetwork() {
        Toast.makeText(this.context, R.string.msg_error_network, Toast.LENGTH_LONG).show()
    }

    override fun setDataRv(payments: List<Payment>) {
        adapter.setData(payments)
    }
}