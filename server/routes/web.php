<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AbsensiController;
use App\Http\Controllers\MahasiswaController;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/test', function(){
    return "Connected.";
});

Route::get('/addrecord', [AbsensiController::class, 'addRecord']);
Route::get('/checkmode', [MahasiswaController::class, 'check_machine']);
Route::get('/registercard', [MahasiswaController::class, 'register_card']);