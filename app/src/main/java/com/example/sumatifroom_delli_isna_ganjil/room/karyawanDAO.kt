package com.example.sumatifroom_delli_isna_ganjil.room

import androidx.room.*

@Dao
interface karyawanDAO {

    @Insert
    fun addkaryawan (tbkar:tb_karyawan)

    @Update
    fun updatekaryawan(tbkar:tb_karyawan)

    @Delete
    fun delkaryawan (tbkar: tb_karyawan)

    @Query("SELECT * FROM tb_karyawan")
    fun tampilsemua():List<tb_karyawan>


    @Query("SELECT * FROM tb_karyawan WHERE id=:tbkar_id")
     suspend fun tampil(tbkar_id:Int):List<tb_karyawan>

    @Query("SELECT * FROM tb_karyawan ORDER BY id,nama ASC")
    suspend fun URUT():List<tb_karyawan>





}