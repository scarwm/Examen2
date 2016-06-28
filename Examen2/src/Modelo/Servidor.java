/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.ListAdapter;

/**
 *
 * @author Scarlett
 */
public class Servidor extends Thread {

    private ServerSocket server;
    private Socket cliente;
    private final int port = 9020;
    private Thread hilito;
    private int numSocket = 1;
    private ArrayList<Socket> listaDeClientes;
    private HashMap<String,ArrayList> hashmap;

    public Servidor() throws IOException {
        this.server = new ServerSocket(port);
        listaDeClientes = new ArrayList<>();
        hashmap=new HashMap<>();
    }

    public void escuchar() {
        while (true) {
            System.out.println("Escuchando....");
            try {
                cliente = server.accept();
                //hilito = new Hilo("hilo" + numSocket, cliente);
                System.out.println("Escuchando hilo" + numSocket);
                listaDeClientes.add(cliente);
                numSocket = numSocket + 1;
                Runnable run = new Hilo(cliente, listaDeClientes);
                hilito=new Thread(run);
                hilito.start();
            } catch (IOException ex) {}

        }
    }
      public static void main(String[] args) {
        Servidor servidor;
        try {
            servidor = new Servidor();
            servidor.escuchar();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
