package com.example.paymentsapp.paymentsscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paymentsapp.network.Payment
import com.example.paymentsapp.R

class PaymentsAdapter : RecyclerView.Adapter<PaymentsAdapter.ViewHolder>() {
    private var listPayment: List<Payment> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tv_id)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvAmount: TextView = view.findViewById(R.id.tv_amount)
        val tvCreated: TextView = view.findViewById(R.id.tv_created)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_payment, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.append(formatStr(listPayment[position].title))
        viewHolder.tvId.append(formatStr(listPayment[position].id))
        viewHolder.tvAmount.append(formatStr(listPayment[position].amount))
        viewHolder.tvCreated.append(formatStr(listPayment[position].created))
    }

    override fun getItemCount(): Int = listPayment.size

    fun setData(listPayment: List<Payment>) {
        this.listPayment = listPayment
        notifyDataSetChanged()
    }

    private fun formatStr(str: String?): String {
        return if (str == "" || str == null)
            "no data"
        else str
    }

}

