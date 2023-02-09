package com.example.sumatifroom_delli_isna_ganjil.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class tb_karyawan(
    @PrimaryKey
    val id : Int,
    val nama :String,
    val alamat :String,
    val usia : String
)