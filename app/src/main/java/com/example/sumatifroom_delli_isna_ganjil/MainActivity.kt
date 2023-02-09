package com.example.sumatifroom_delli_isna_ganjil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_delli_isna_ganjil.room.Constant
import com.example.sumatifroom_delli_isna_ganjil.room.codepelita
import com.example.sumatifroom_delli_isna_ganjil.room.tb_karyawan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy {codepelita(this)}
    private lateinit var karyAdapter :karyawanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halpindah()
        setupRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        loadkaryawan()
    }
    fun loadkaryawan(){
        CoroutineScope(Dispatchers.IO).launch {
            val kn = db.karDAO().tampilsemua()
            Log.d("MainActivity","dbresponse: $kn")
            withContext(Dispatchers.Main){
                karyAdapter.setData(kn)
            }
        }
    }


    private fun halpindah(){
        IB_input.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }
    fun intentEdit(tbkarId: Int,intentType:Int){
        startActivity(
            Intent(applicationContext,PindahActivity::class.java)
                .putExtra("intent_id",tbkarId)
                .putExtra("intent_type",intentType)
        )
    }
    private fun setupRecyclerView(){
        karyAdapter= karyawanAdapter(arrayListOf(),object :karyawanAdapter.onAdapterListener{
            override fun onClick(tbkar: tb_karyawan) {
              intentEdit(tbkar.id,Constant.TYPE_READ)
            }

            override fun onUpdate(tbkar: tb_karyawan) {
                intentEdit(tbkar.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbkar: tb_karyawan) {
               dialog(tbkar)
            }

        })
        listkaryawan.apply {
            layoutManager= LinearLayoutManager(applicationContext)
            adapter = karyAdapter
        }
    }
    private fun dialog(tbkar : tb_karyawan){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")

            setMessage("Yakin Hapus ${tbkar.nama} ?")

            setNegativeButton("Batal"){dialogInterface,i ->
               dialogInterface.dismiss()
            }

            setPositiveButton("Hapus"){dialogInterface,i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.karDAO().delkaryawan(tbkar)
                    loadkaryawan()
                }
            }

        }
        alertDialog.show()
    }
}