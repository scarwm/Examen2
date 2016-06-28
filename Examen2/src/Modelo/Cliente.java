/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.VistaChat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Scarlett
 */
public class Cliente implements Runnable{
    
    private Socket cliente;
    private DataInputStream entradaSocket;
    private DataOutputStream salidaSocket;
    private String mensajes="";
    
    private String host="localHost";
    private int port=9020;
    
    private VistaChat vistaChat;
    private String nombreDeCliente;

    public Cliente(VistaChat vista) throws IOException {
        this.vistaChat=vista;
        this.nombreDeCliente=vistaChat.getjTextNombre();
       
        this.cliente=new Socket(host,port);
        
        this.entradaSocket=new DataInputStream(cliente.getInputStream());
        this.salidaSocket=new DataOutputStream(cliente.getOutputStream());
        this.salidaSocket.writeUTF("Esto es lo que el servidor recibe- \n");
        
    }
    
    public void run(){
      
        while(true){
            try {
                mensajes+=entradaSocket.readUTF();
                vistaChat.setjTextArea1(mensajes);//texto que viene de servidor
            } catch (IOException ex) {
                System.err.println("algo ocurre mal");
            }
        }
    }
    
    public void enviarMSG(String msg){
        try {
            this.salidaSocket.writeUTF(msg+"\n");
        } catch (IOException ex) {
            System.err.println("algo salio mal en enviarMSG");
        }
    }//envia msg al servidor
    
    
    
    
    
    
}
