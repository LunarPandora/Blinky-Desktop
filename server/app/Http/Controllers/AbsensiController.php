<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use Carbon\Carbon;

class AbsensiController extends Controller
{
    private function hariIndo($hariInggris) {
        switch ($hariInggris) {
          case 'Sunday':
            return 'Minggu';
          case 'Monday':
            return 'Senin';
          case 'Tuesday':
            return 'Selasa';
          case 'Wednesday':
            return 'Rabu';
          case 'Thursday':
            return 'Kamis';
          case 'Friday':
            return 'Jumat';
          case 'Saturday':
            return 'Sabtu';
          default:
            return 'hari tidak valid';
        }
      }

    public function addRecord(Request $request){
        $request->validate([
            'uid_rfid' => 'required|string'
        ]);

        $mhswa = DB::table('tb_mahasiswa')
        ->select('id_mhswa', 'id_kelas')
        ->where('uid_rfid', $request->uid_rfid)->first();

        $today = $this->hariIndo(date('l'));
        $jam = Carbon::now()->format("H:i:s");

        $jadwal = DB::table('tb_jadwal')
        ->select('id_jadwal')
        ->where([
            ["id_kelas", "=", $mhswa->id_kelas],
            ["hari", "=", $today],
            ["jam_mulai", "<=", $jam],
            ["jam_selesai", ">=", $jam]
        ])->first();

        if(!is_null($jadwal)){
            $absensi = array(
                'id_mhswa' => $mhswa->id_mhswa,
                'id_jadwal' => $jadwal->id_jadwal,
                'waktu_absen' => Carbon::now(),
                'id_status_absensi' => 1
            );

            if(DB::table('tb_absensi')->insert($absensi)){
                return response("success");
            }
            else{
                return response("failed");
            }
        }
        else{
            return response("failed");
        }
        
        // return response([$mhswa->id_mhswa, $mhswa->id_kelas, $today, $jam, $jadwal]);
    }
}
