package cf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by abhishekupadhyay on 2016/02/12.
 */
@SpringBootApplication
@RestController
@EnableResourceServer
class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run (ResourceApplication.class, args);
    }

}
