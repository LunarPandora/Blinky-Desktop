<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Admin extends Model
{
    use HasFactory;

    protected $table = "tb_admin";

    protected $fillable = [
        'id_admin',
        'nm_admin',
        'u_admin',
        'pw_admin',
        'foto_admin',
    ];
}
