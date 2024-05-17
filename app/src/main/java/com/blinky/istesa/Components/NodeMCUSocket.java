package com.blinky.istesa.Components;

import java.io.BufferedReader;
import java.io.IOException;
// import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.fazecast.jSerialComm.*;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.net.Socket;

public class NodeMCUSocket {
    static String host = "192.168.4.1";
    static int portNumber = 80;
    // static SerialPort[] s = SerialPort.getCommPorts();

    public static void main(String[] args){
       
    }
    
    public NodeMCUSocket() {
        // for(SerialPort sp : s){
        //     System.out.println(sp.getSystemPortName());
        // }
        // portName = s[0].getSystemPortName();

        // PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void listenToPort() throws IOException, InterruptedException{

        Socket socket = new Socket(host, portNumber);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while(true){
            System.out.println(br.readLine());

            Thread.sleep(1000);
        }

        // SerialPort sp = SerialPort.getCommPort("COM6");

        // sp.setComPortParameters(9600, 8, 1, 0);
        // sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        // // sp.

        // if(sp.openPort()){
        //     System.out.println("Socket port connection success.");
        // }
        // // else{
        // //     System.out.println("Socket port connection failed.");
        // //     return;
        // // }    
        
        // InputStream comInput = sp.getInputStream();
        // int byteAsc;
        // do{
        //     byteAsc = comInput.read();
        //     System.out.print((char) byteAsc);
        // }
        // while(byteAsc != 10);

        // if(sp.closePort()){
        //     System.out.println("Port is closed");
        // }
        // else{
        //     System.out.println("Port is force-closed");
        // }
    }
}
