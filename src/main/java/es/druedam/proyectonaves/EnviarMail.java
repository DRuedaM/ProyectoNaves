package es.druedam.proyectonaves;

import javax.mail.*;
import javax.mail.internet.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class EnviarMail
{
    private static String emailFrom = "davidruedamartin26@gmail.com";
    private static String passwordFrom = "npxr nktt usez xfwm";

    private static Session mSession;
    private static MimeMessage mCorreo;
    private static MimeBodyPart mimeBodyPart;

    public static void recogerDatosYEnviar()
    {
        ArrayList<Invitacion> listaInvitaciones = DatabaseManager.recogerAlumnos();
        ArrayList<String> listaCodigos = new ArrayList<>();

        for(Invitacion alumno : listaInvitaciones)
        {
            enviarMail(alumno.getCorreo(), alumno.getListaCodigos());
        }


    }


    private static void enviarMail(String direccionCorreo, ArrayList<String> listaCodigos)
    {
        try
        {
            Properties mProperties = getProperties();

            mSession = Session.getDefaultInstance(mProperties);
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));

            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(direccionCorreo));
            mCorreo.setSubject("Invitaciones Graduación Las Naves 2024");
            StringBuilder codigos = new StringBuilder();
            for(String codigo : listaCodigos)
            {
                codigos.append(codigo).append(" ");
            }
            mCorreo.setText(codigos.toString(), "ISO-8859-1", "html");

            //mimeBodyPart.attachFile();

            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
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
