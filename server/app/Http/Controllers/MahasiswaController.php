<?php

namespace App\Http\Controllers;

use App\Models\Mahasiswa;

use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;

class MahasiswaController extends Controller
{
    public function check_machine(){
        $m = DB::table('machine')->get();
        
        return $m[0]->is_scanning;
    }

    public function register_card(Request $request){

        $tmp_ms = DB::table('machine')->update([
            'last_rec_rfid' => $request->uid_rfid
        ]);

        return "success";
    }
}
