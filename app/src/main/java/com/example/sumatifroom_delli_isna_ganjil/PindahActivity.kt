package com.example.sumatifroom_delli_isna_ganjil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sumatifroom_delli_isna_ganjil.room.Constant
import com.example.sumatifroom_delli_isna_ganjil.room.codepelita
import com.example.sumatifroom_delli_isna_ganjil.room.tb_karyawan
import kotlinx.android.synthetic.main.activity_pindah.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PindahActivity : AppCompatActivity() {

    val db by lazy { codepelita(this) }
    private var tbkarId : Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pindah)
        setupView()
        tombolperintah()
        tbkarId=intent.getIntExtra("intent_id",0)
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when(intentType){
            Constant.TYPE_CREATE->{
                Ib_update.visibility=View.GONE

            }
            Constant.TYPE_READ->{
                Ib_save.visibility= View.GONE
                Ib_update.visibility=View.GONE
                tampil()
            }
            Constant.TYPE_UPDATE->{
                Ib_save.visibility= View.GONE
                tampil()
            }
        }

    }
    fun tombolperintah(){
        Ib_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.karDAO().addkaryawan(
                    tb_karyawan(ETid.text.toString().toInt(),ETnama.text.toString(),
                    ETalamat.text.toString(),ETusia.text.toString())
                )
                finish()
            }
        }
        Ib_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.karDAO().updatekaryawan(
                    tb_karyawan(ETid.text.toString().toInt(),ETnama.text.toString(),
                        ETalamat.text.toString(),ETusia.text.toString())
                )
                finish()
            }
        }
    }
    fun tampil(){
        tbkarId=intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val yows = db.karDAO().tampil(tbkarId)[0]
            val dataid: String = yows.id.toString()
            ETid.setText(dataid)
            ETnama.setText(yows.nama)
            ETalamat.setText(yows.alamat)
            ETusia.setText(yows.usia)

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}