<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Kelas extends Model
{
    use HasFactory;

    protected $table = "tb_kelas";

    protected $fillable = [
        'id_kelas',
        'nm_kelas'
    ];
}
