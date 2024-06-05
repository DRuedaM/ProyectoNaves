package es.druedam.proyectonaves;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
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
        ArrayList<File> listaCodigos = new ArrayList<>();
        for(Codigo alumno : listaInvitaciones)
        {
            int index = 0;
            for(String codigo : alumno.getListaCodigos())
            {
                listaCodigos.add(new QRGenerator().generarQR(codigo, alumno.getCorreo(), index++));
            }
            enviarMail(alumno.getCorreo(), alumno.getListaCodigos(), new ArrayList<>(listaCodigos));
            listaCodigos.clear();
        }
    }


    private static void enviarMail(String direccionCorreo, ArrayList<String> listaCodigos, ArrayList<File> listaQRS)
    {
        try
        {
            Properties mProperties = getProperties();

            mSession = Session.getDefaultInstance(mProperties);
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));

            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(direccionCorreo));
            mCorreo.setSubject("Invitaciones Graduación Las Naves Salesianos 2024 (24 Mayo)");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hola, estas son tus invitaciones para la graduación");

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
