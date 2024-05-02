//  Step #0
//  Include semua library yang akan digunakan.
#include <UrlEncode.h>

#include <SPI.h>
#include <MFRC522.h>

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ESP8266WebServer.h>
#include <WiFiClient.h>
#include <LiquidCrystal_I2C.h>

//  Step #1
//  Define semua pin yang digunakan
#define RST_PIN D3 // Pin untuk mereset MFRC522
#define SDA_PIN D4 // Pin untuk mengambil data dari MFRC522

#define BUZZ D8 // Pin Buzzer
// #define BUTTON D8 // Pin Button
LiquidCrystal_I2C lcd(0x27, 16, 2);

//  Step #2
//  Masukkan SSID dan Password
const char* ssid = "RFID1";
const char* password = "rfid1234";

//  Step #3
//  Siapkan class yang akan digunakan untuk mengakses
//  Board, server dan sensor
ESP8266WebServer server(80);

MFRC522 mfrc522(SDA_PIN, RST_PIN);
// MFRC522::MIFARE_Key key;

// int blockNum = 2;

// byte bufferLen = 18;
// byte readBlockData[18];

// MFRC522::StatusCode status;

WiFiClient client;
HTTPClient http;

void setup() {
  //  Mulai terima output serial di serial monitor -> 115200 baud
  Serial.begin(115200);
  pinMode(BUZZ, OUTPUT);

  //  Step #4
  //  Siapkan Access Point NodeMCU dengan SSID dan Password AP
  //  yang sudah kita siapkan di awal
  Serial.println("Setting AP (Access Point)…");
  WiFi.softAP(ssid, password);
  delay(100);

  //  Step #5 (opsional)
  //  Tampilkan IP dari WiFi AP kita
  IPAddress IP = WiFi.softAPIP();
  Serial.println("AP IP address: ");
  Serial.println(IP);

  //  Step #6
  //  Atur LED ke mode output dan pastikan 2 LED dalam
  //  keadaan mati atau LOW  

  //  Step #7
  //  Siapkan rute web server yang dapat digunakan
  //  untuk mengecek apakah web server terkoneksi
  //  (Silahkan tambah rute lain jika diperlukan)
  //
  //  === Cara kerja ===
  //
  //  .on menerima 2 parameter, yaitu URL dan handler (function)
  //  Contoh : server.on("/home", halamanHome)
  //  Hasil : Jika client membuka halaman "/home", maka akan tampil
  //          halaman home. 
  server.on("/", handleRoot);

  //  Step #8
  //  Jalankan layanan HTTP Server
  server.begin();
  Serial.println("HTTP server started");

  //  Step #9
  //  Jalankan proses scanner dan inisialisasi
  //  Sensor MFRC522
  SPI.begin();
  mfrc522.PCD_Init();
  Serial.println("Put your card to the reader...");

  lcd.init();
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("Silahkan scan"); 
  lcd.setCursor(0, 1);
  lcd.print("kartu anda..."); 
}

void loop() {
  //  Step #10
  //  Selama board berjalan, server terus menerus
  //  Menerima koneksi dari client
  server.handleClient();
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("Silahkan scan"); 
  lcd.setCursor(0, 1);
  lcd.print("kartu anda..."); 

  //  Step #11
  //  Selama tidak ada kartu yang terscan
  //  Maka kembali ke step #10
  if(!mfrc522.PICC_IsNewCardPresent()){
    return;
  }

  if(!mfrc522.PICC_ReadCardSerial()){
    return;
  }
  else{
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Absensi anda"); 
    lcd.setCursor(0, 1);
    lcd.print("sedang diproses"); 

    //  Step #12
    //  Jika ada kartu yang terscan, maka ambil
    //  UID tag dari kartu RFID
    Serial.print("UID tag : ");
    String content = "";
    byte letter;

    // for (byte i = 0; i < 6; i++)
    // {
    //   key.keyByte[i] = 0xFF;
    // }

    for(byte i = 0; i < mfrc522.uid.size; i++){
      Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
      Serial.print(mfrc522.uid.uidByte[i], HEX);
      content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
      content.concat(String(mfrc522.uid.uidByte[i], HEX));
    }

    // byte blockData [16] = {"tes"};
    // Serial.println(generateRandomString());

    // Serial.print("\n");
    // Serial.println("Writing to Data Block...");
    // WriteDataToBlock(blockNum, blockData);

    // Serial.print("\n");
    // Serial.println("Reading from Data Block...");
    // ReadDataFromBlock(blockNum, readBlockData);

    content.toUpperCase();
    String uid = content.substring(1);

    //  Step #13
    //  Siapkan URL dan data yang akan dikirim
    //  ke web server localhost (XAMPP)
    String getData = "uid_rfid=" + urlEncode(uid) ;
    String Link = "http://192.168.4.2:8000/addrecord?" + getData;

    Serial.println("\n" + Link);
    
    //  Step #14
    //  Kirimkan request ke server
    http.begin(client, Link.c_str());
    int httpCode = http.GET(); 
    
    //  Step #15
    //  Ambil response code dan payload
    String payload = http.getString(); 

    //  Step #16
    //  Cetak kode response HTTP
    //  dan payload, setelah itu tutup koneksi HTTP
    Serial.println(httpCode);
    Serial.println(payload);
    http.end();

    //  Step #17
    //  Cek payload, jika success maka nyalakan LED hijau
    //  jika tidak, maka nyalakan LED merah
    if(httpCode != -1){
      if(payload == "success"){
        tone(BUZZ, 587, 100);
        tone(BUZZ, 880, 100);

        Serial.println("Kartu cocok");
        lcd.clear();
        lcd.setCursor(0, 0);
        lcd.print("Absensi anda"); 
        lcd.setCursor(0, 1);
        lcd.print("berhasil!"); 

        Serial.println(content);
        delay(2000);

      }
      else if(payload == "failed"){
        tone(BUZZ, 587, 300);

        Serial.println("Kartu tidak cocok");
        lcd.clear();
        lcd.setCursor(0, 0);
        lcd.print("Absensi anda"); 
        lcd.setCursor(0, 1);
        lcd.print("tidak berhasil!"); 
        delay(2000);
      }

    //  Step #18
    //  Berikan delay untuk memproses data
    }
    else{
      tone(BUZZ, 587, 300);

      lcd.clear();

      lcd.setCursor(0, 0);
      lcd.print("Server");
      lcd.setCursor(0, 1);
      lcd.print("Not Found!");
    }

    // Serial.print("\n");
    // Serial.print("Data in Block:");
    // Serial.print(blockNum);
    // Serial.print(" --> ");
    // for (int j=0 ; j<16 ; j++)
    // {
    //   Serial.write(readBlockData[j]);
    // }
    // Serial.print("\n");
  }

  delay(3000);
}


//  Step #19
//  Buat sebuah function untuk mengembalikan
//  request dari browser yang membuka route.
void handleRoot(){
  server.send(200, "text/html", "<h1>You are connected</h1>");
}

// void WriteDataToBlock(int blockNum, byte blockData[]) 
// {
//   /* Authenticating the desired data block for write access using Key A */
//   status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, blockNum, &key, &(mfrc522.uid));
//   if (status != MFRC522::STATUS_OK)
//   {
//     Serial.print("Authentication failed for Write: ");
//     Serial.println(mfrc522.GetStatusCodeName(status));
//     return;
//   }
//   else
//   {
//     Serial.println("Authentication success");
//   }

  
//   /* Write data to the block */
//   status = mfrc522.MIFARE_Write(blockNum, blockData, 16);
//   if (status != MFRC522::STATUS_OK)
//   {
//     Serial.print("Writing to Block failed: ");
//     Serial.println(mfrc522.GetStatusCodeName(status));
//     return;
//   }
//   else
//   {
//     Serial.println("Data was written into Block successfully");
//   }
  
// }

// void ReadDataFromBlock(int blockNum, byte readBlockData[]) 
// {
//   /* Authenticating the desired data block for Read access using Key A */
//   byte status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, blockNum, &key, &(mfrc522.uid));

//   if (status != MFRC522::STATUS_OK)
//   {
//      Serial.print("Authentication failed for Read: ");
//      Serial.println(mfrc522.GetStatusCodeName((MFRC522::StatusCode)status));
//      return;
//   }
//   else
//   {
//     Serial.println("Authentication success");
//   }

//   /* Reading data from the Block */
//   status = mfrc522.MIFARE_Read(blockNum, readBlockData, &bufferLen);
//   if (status != MFRC522::STATUS_OK)
//   {
//     Serial.print("Reading failed: ");
//     Serial.println(mfrc522.GetStatusCodeName((MFRC522::StatusCode)status));
//     return;
//   }
//   else
//   {
//     Serial.println("Block was read successfully");  
//   }
// }