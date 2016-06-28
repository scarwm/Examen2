/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scarlett
 */
public class Hilo implements Runnable{

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ArrayList<Socket> lista;
    
    
    public Hilo(Socket socket, ArrayList lista) {
        this.socket=socket;
        this.lista=lista;
    }
    
    
   
    @Override
    public void run() {
        try {
            this.in=new DataInputStream(socket.getInputStream());
            this.out=new DataOutputStream(socket.getOutputStream());
            while(true){
                String recibido=in.readUTF();
                for(int i=0;i<lista.size();i++){
                    out=new DataOutputStream(lista.get(i).getOutputStream());
                    out.writeUTF(recibido);
                }
            }
        } catch (IOException ex) {
        }
        
    }
}
