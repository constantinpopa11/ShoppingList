/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SSLMailSender {

    public static void sendVerificationMail(String recipientAddress) {

        final String username = "noreply.shoppinglists@gmail.com";
        final String password = "bybfbnuyypvfmzwd";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);

            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply.shoppinglists@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientAddress));
            message.setSubject("Verify your email address");

            UUID code = UUID.randomUUID();

            message.setText("Welcome!\n"
                    + "\n"
                    + "Thanks for signing up! We just need you to verify your "
                    + "email address to complete setting up your account.\n"
                    + "\n"
                    + "localhost:8080/ShoppingListApp/VerifyEmail?code=" + code);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
