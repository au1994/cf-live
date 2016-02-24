package cf;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cf.CfService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by abhishekupadhyay on 2016/02/16.
 */
@RestController
@RequestMapping(value="/urr")
class UserController {

    private final CfService cfService;

    @Autowired
    UserController(CfService cfService)
    {
        this.cfService=cfService;
    }

    @RequestMapping(value="/ur")
    public JSONArray home() {


        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MailSenderConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
        try {
            mailMsg.setFrom("abhishek@craftemporio.com");
            mailMsg.setTo("abhishek.upadhyay.cse12@iitbhu.ac.in");
            mailMsg.setSubject("Test mail From CraftEmporio");
            mailMsg.setText("Hello World!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
        System.out.println("---Done---");






        JSONArray jsarr = new JSONArray();
        try {
            jsarr = cfService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsarr;
    }
    }





