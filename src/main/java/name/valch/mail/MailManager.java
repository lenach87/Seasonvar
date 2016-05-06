package name.valch.mail;

import name.valch.entity.SerialWithDates;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MailManager {

    private JavaMailSender sender;

    public void setSender(JavaMailSender sender){
        this.sender = sender;
    }

    public void sendNotification(List<String> emails, SerialWithDates serial){
        String text = getNotificationText(serial);
        for (String s : emails){
            sendNotification(s, text);
        }
    }

    private void sendNotification(String mail, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);
        msg.setSubject("New series available");
        msg.setText(text);
        sender.send(msg);
    }

    private String getNotificationText(SerialWithDates serial){
        String template;
        try {
            InputStream is = this.getClass().getResourceAsStream("/MessageTemplate.txt");
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String read;
            while ((read = br.readLine()) != null) {
                sb.append(read);
            }
            br.close();
            template = sb.toString();
        } catch (IOException ex)
        {
            ex.printStackTrace();
            template = "New series of %1$s added link %2$s";
        }
        return String.format(template, serial.getName(), serial.getLink());
    }
}
