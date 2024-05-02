<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Mahasiswa extends Model
{
    use HasFactory;

    protected $table = "tb_mahasiswa";

    protected $fillable = [
        'id_mhswa',
        'id_kelas',
        'id_prodi',
        'nm_mhswa',
        'angkatan',
        'foto_mhswa',
        'id_admin',
        'pw_mhswa',
        'uid_rfid'
    ];
}
