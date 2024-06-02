package es.druedam.proyectonaves;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class EnviarMail
{
    private static String emailFrom = "davidruedamartin26@gmail.com";
    private static String passwordFrom = "npxr nktt usez xfwm";

    private static Session mSession;
    private static Message mCorreo;
    private static MimeBodyPart mimeBodyPart = new MimeBodyPart();

    public static void recogerDatosYEnviar()
    {
        ArrayList<Codigo> listaInvitaciones = Conexion.recogerAlumnos();

        for(Codigo alumno : listaInvitaciones)
        {
            for(String codigo : alumno.getListaCodigos())
            {
                enviarMail(alumno.getCorreo(), alumno.getListaCodigos(), new QRGenerator().generarQR(codigo, alumno.getCorreo()));
            }
        }


    }


    private static void enviarMail(String direccionCorreo, ArrayList<String> listaCodigos, File QR)
    {
        try
        {
            Properties mProperties = getProperties();

            mSession = Session.getDefaultInstance(mProperties);
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));

            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(direccionCorreo));
            mCorreo.setSubject("Invitaciones Graduación Las Naves 2024");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hola, estas son tus invitaciones para la graduacion");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(QR);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("boliviano.png");
            multipart.addBodyPart(messageBodyPart);

            mCorreo.setContent(multipart);

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
