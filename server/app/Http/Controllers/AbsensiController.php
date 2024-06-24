<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use Carbon\Carbon;

class AbsensiController extends Controller
{
    private function hari($hariInggris) {
        switch ($hariInggris) {
          case 'Sunday':
            return '0';
          case 'Monday':
            return '1';
          case 'Tuesday':
            return '2';
          case 'Wednesday':
            return '3';
          case 'Thursday':
            return '4';
          case 'Friday':
            return '5';
          case 'Saturday':
            return '6';
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

        if(!is_null($mhswa)){
            $today = $this->hari(date('l'));
            $jam = Carbon::now();
    
            $jadwal = DB::table('tb_jadwal')
            ->select('id_jadwal', 'jam_mulai', 'jam_selesai')
            ->where([
                ["id_kelas", "=", $mhswa->id_kelas],
                ["hari", "=", $today],
                ["jam_mulai", ">=", "DATE_SUB('" . $jam->format("H:i:s") . "', INTERVAL 15 MINUTES)"],
                ["jam_selesai", ">=", $jam->format("H:i:s")]
            ])->first();
    
            if(!is_null($jadwal)){
                $waktuMulai = Carbon::createFromTimeString($jadwal->jam_mulai);
                $waktuSelesai = Carbon::createFromTimeString($jadwal->jam_selesai);
    
                $waktuAbsen = Carbon::createFromTimeString($jadwal->jam_mulai)->subMinutes(15);

                if($jam >= $waktuAbsen && $jam <= $waktuSelesai){
                    if($jam >= $waktuAbsen && $jam <= $waktuMulai){
                        $absensi = array(
                            'id_mahasiswa' => $mhswa->id_mhswa,
                            'id_jadwal' => $jadwal->id_jadwal,
                            'waktu_absen' => Carbon::now(),
                            'kode_status_absensi' => 1
                        );
    
                        if(DB::table('tb_absensi')->insert($absensi)){
                            return response("success");
                        }
                        else{
                            return response("failed");
                        }
                    }
                    else{
                        $absensi = array(
                            'id_mahasiswa' => $mhswa->id_mhswa,
                            'id_jadwal' => $jadwal->id_jadwal,
                            'waktu_absen' => Carbon::now(),
                            'kode_status_absensi' => 2
                        );
    
                        if(DB::table('tb_absensi')->insert($absensi)){
                            return response("success");
                        }
                        else{
                            return response("failed");
                        }
                    }
                }
                else{
                    return response("failed");
                }
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
