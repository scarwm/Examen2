/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Vista.VistaChat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scarlett
 */
public class Control implements ActionListener {

    private Cliente Cliente;
    private Thread hilo;
    private VistaChat vis;
    private String nombre;

    public Control(VistaChat vista) {
        this.vis = vista;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("Conectar")) {

            try {

                this.Cliente = new Cliente(vis);
                this.hilo=new Thread(Cliente);
                hilo.start();
                vis.setNombre();
                vis.limpiar();
            } catch (IOException ex) {
            }

        }if(ae.getActionCommand().equalsIgnoreCase("Enviar")){
            this.nombre=vis.getNombre();
            this.Cliente.enviarMSG(nombre+":  "+vis.getTextMsg()+"\n");
        }
    }

}
