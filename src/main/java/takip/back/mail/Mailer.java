package takip.back.mail;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class Mailer {
    JavaMailSender jms;

    @PostConstruct
    public void setJms(){
        var sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);

        sender.setUsername("ataystest@gmail.com");
        sender.setPassword("krivxlnymbgkconn");

        var props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        jms = sender;
    }

    public void sendSimpleMessage(String to) {
        var message = jms.createMimeMessage();
        QRCodeWriter writer = new QRCodeWriter();

        try {
            MimeMessageHelper h = new MimeMessageHelper(message,true);
            h.setFrom("ben");
            h.setTo(to);
            h.setSubject("qr-test");
            h.setText("qr attached");

            BitMatrix bitMatrix = writer.encode("This is a new qr code", BarcodeFormat.QR_CODE, 200, 200);

            File file = new File("temporaryQR.png");
            ImageIO.write(
                    MatrixToImageWriter.toBufferedImage(bitMatrix),
                    "png",
                    file
            );


            FileSystemResource attachment = new FileSystemResource(file);
            h.addAttachment("Invoice",attachment);

        }catch (Exception e){
            e.printStackTrace();
        }


        jms.send(message);
    }
}
