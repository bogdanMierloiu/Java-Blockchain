package ro.bogdan_mierloiu.javablockchain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class JavaBlockchainApplication {


    public static void main(String[] args) {
        SpringApplication.run(JavaBlockchainApplication.class, args);
    }

}
