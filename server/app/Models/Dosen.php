<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Dosen extends Model
{
    use HasFactory;

    protected $table = "tb_dosen";

    protected $fillable = [
        'id_dosen',
        'nm_dosen',
        'u_dosen',
        'pw_dosen',
        'foto_dosen',
        'nidn',
        'id_status_absensi',
    ];
}
