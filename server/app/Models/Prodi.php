<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Prodi extends Model
{
    use HasFactory;

    protected $table = "tb_prodi";

    protected $fillable = [
        'id_prodi',
        'nm_prodi',
    ];
}
