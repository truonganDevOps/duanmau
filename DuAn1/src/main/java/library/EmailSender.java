/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;

public class EmailSender {
    
    
    public static void emailSender(String recipient, String usernameF, String otp) {
        // Set up mail server and properties
        String host = "smtp.gmail.com"; // SMTP server host
        final String username = "kaisamaslain+Nerdyers@gmail.com";
        final String password = "oanxsumpkcoisyrx";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587"); // Port for TLS

        // Create a session with an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Set the sender and recipient
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set the subject and text
            message.setSubject("Password Reset OTP Request");
            message.setText("Dear " + usernameF +",\n"
                    + "\n"
                    + "Chúng tôi đã nhận được yêu cầu của bạn để đặt lại mật khẩu cho tài khoản của mình. Đây là mã OTP (One-Time Password) của bạn: "+ otp +".\n"
                    + "\n"
                    + "Vui lòng nhập mã OTP này trong trang đặt lại mật khẩu để tiếp tục quá trình đặt lại.\n"
                    + "\n"
                    + "Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này. Đảm bảo rằng bạn bảo mật mã OTP này và không tiết lộ nó cho bất kỳ ai khác.\n"
                    + "\n"
                    + "Nếu bạn gặp bất kỳ vấn đề nào hoặc cần sự trợ giúp, vui lòng liên hệ với chúng tôi qua địa chỉ email này.\n"
                    + "\n"
                    + "Trân trọng,\n"
                    + "CEO Nerdyers: Vũ Anh Khoa\n"
                    + "kaisamaslain@gmail.com\n"
                    + "0363457017");

            // Send the message
            Transport.send(message);
            DialogHelper.alert(null, "Email sent successfully!");

        } catch (MessagingException e) {
            
            DialogHelper.alert(null, "Email sent failed!");
            e.printStackTrace();
        }
    }
}
