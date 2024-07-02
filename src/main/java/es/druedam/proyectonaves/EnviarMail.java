package es.druedam.proyectonaves;


import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

public class EnviarMail
{
    private static String emailFrom = "intranet@salesianosalcala.com";
    private static String passwordFrom = "mvga mbdh spmm rybs";

    private static Session mSession;
    private static Message mCorreo;
    private static MimeBodyPart mimeBodyPart = new MimeBodyPart();

    public static void recogerDatosYEnviar(String textCorreoGmail, String textClave, String asunto, String contenido)
    {
         emailFrom = textCorreoGmail;
        passwordFrom = textClave;
        ArrayList<Codigo> listaInvitaciones = Conexion.recogerAlumnos();
        ArrayList<File> listaCodigos = new ArrayList<>();
        for(Codigo alumno : listaInvitaciones)
        {
            int index = 0;
            for(String codigo : alumno.getListaCodigos())
            {
                listaCodigos.add(new QRGenerator().generarQR(codigo, alumno.getCorreo(), index++));
            }
            enviarMail(alumno.getCorreo(), alumno.getListaCodigos(), new ArrayList<>(listaCodigos), asunto, contenido);
            listaCodigos.clear();
        }
    }


    private static void enviarMail(String direccionCorreo, ArrayList<String> listaCodigos, ArrayList<File> listaQRS, String asunto, String contenido)
    {
        try
        {
            Properties mProperties = getProperties();

            mSession = Session.getDefaultInstance(mProperties);
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));

            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(direccionCorreo));
            mCorreo.setSubject(asunto);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(contenido);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            int index = 0;
            for(File qr : listaQRS)
            {
                messageBodyPart = new MimeBodyPart();
                FileDataSource source = new FileDataSource(qr);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName("InvitacionGraduacion2024_" + index++ + ".png");
                multipart.addBodyPart(messageBodyPart);
            }


            mCorreo.setContent(multipart);

            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            Conexion.updateCorreoEnviado(direccionCorreo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static Properties getProperties() {
        Properties mProperties = new Properties();
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.auth", "true");
        return mProperties;
    }
}
