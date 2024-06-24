<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Absensi extends Model
{
    use HasFactory;

    protected $table = "tb_absensi";

    protected $fillable = [
        'id_mahasiswa',
        'id_jadwal',
        'waktu_absen',
        'kode_status_absensi',
    ];
}
