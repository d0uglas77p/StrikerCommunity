package application.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public static boolean sendEmail(String to, String subject, String body, String imagePath) {
        // Configura propriedades do e-mail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Cria uma sessão com autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Cria uma mensagem
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Cria a parte do corpo da mensagem
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html; charset=utf-8");

            // Cria a parte da imagem embutida
            MimeBodyPart imagePart = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(new File(imagePath));
            imagePart.setDataHandler(new DataHandler(fds));
            imagePart.setHeader("Content-ID", "<logoemail>");

            // Junta as partes em uma mensagem completa
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(imagePart);

            // Adiciona o conteúdo da mensagem
            message.setContent(multipart);

            // Envia o e-mail
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String loadEmailTemplate(String filePath, String nomePerfil) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            // Substitui o placeholder com o valor real
            return content.replace("${nomePerfil}", nomePerfil);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
