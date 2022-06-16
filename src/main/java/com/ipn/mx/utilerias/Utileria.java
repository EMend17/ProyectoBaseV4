/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author EMend17
 */
public class Utileria {

    public void enviarMail(String correoDestinatario, String Asunto, String textoCorreo) {
        try {
            //Propiedades de la conexion
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");

            //Inicializar la Sesion 
            Session session = Session.getDefaultInstance(props);

            //El mensaje
            MimeMessage message = new MimeMessage(session);
           // message.setFrom(new InternetAddress("proyecto.tecweb.equipo9@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(correoDestinatario));
            //CC: A quien se le envia una copia oculta
            //BCC: A quien se le envia una copia oculta

            message.setSubject(Asunto);
            message.setText(textoCorreo);
            //Envio Mensaje
            Transport transporte = session.getTransport("smtp");
            transporte.connect("proyecto.tecweb.equipo9@gmail.com", "contrasenaSegura123");
            transporte.sendMessage(message, message.getAllRecipients());
            //Cierre.
            transporte.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        Utileria emhGmail = new Utileria();
        String correoDestinatario ="emendez0017@gmail.com";
        String asunto = "Registro";
        String textoCorreo = "Hemos recibido satisfactoriamente tu solicitud de registro blablabla....";
        emhGmail.enviarMail(correoDestinatario,asunto,textoCorreo);
        
    }

}
