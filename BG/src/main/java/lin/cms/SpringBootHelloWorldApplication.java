package lin.cms;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableScheduling
@RestController
@EnableSwagger2
@EnableOpenApi
@MapperScan("lin.cms.mapper")
@SpringBootApplication(scanBasePackages = {"lin.cms"})
public class SpringBootHelloWorldApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootHelloWorldApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        String path = environment.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(path)) {
            path = "";
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>> start  global-news  success!address:");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>访问地址：http://{}:{}{}", InetAddress.getLocalHost().getHostAddress(), environment.getProperty("server.port"), path);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>swagger：http://{}:{}{}/swagger-ui/index.html", InetAddress.getLocalHost().getHostAddress(), environment.getProperty("server.port"), path);
    }

}
