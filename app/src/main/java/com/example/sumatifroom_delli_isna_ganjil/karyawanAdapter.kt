package com.example.sumatifroom_delli_isna_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_delli_isna_ganjil.room.tb_karyawan
import kotlinx.android.synthetic.main.activity_karyawan_adapter.view.*

class karyawanAdapter (private val kary:ArrayList<tb_karyawan>,private val listener:onAdapterListener)
    : RecyclerView.Adapter<karyawanAdapter.KaryawanViewHolder>(){


    class KaryawanViewHolder( val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : KaryawanViewHolder {
        return  KaryawanViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_karyawan_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: KaryawanViewHolder, position: Int) {
        val kr = kary[position]
        holder.view.T_namakar.text =kr.nama
        holder.view.T_idkar.text= kr.id.toString()

        holder.view.T_namakar.setOnClickListener {
            listener.onClick(kr)
        }
        holder.view.Iv_edit.setOnClickListener {
            listener.onUpdate(kr)
        }
        holder.view.Iv_delete.setOnClickListener {
            listener.onDelete(kr)
        }

    }

    override fun getItemCount()= kary.size

    fun setData (list:List<tb_karyawan>){
        kary.clear()
        kary.addAll(list)
        notifyDataSetChanged()
    }
    interface  onAdapterListener{
        fun onClick(tbkar: tb_karyawan)
        fun onUpdate(tbkar: tb_karyawan)
        fun onDelete(tbkar: tb_karyawan)
    }


}