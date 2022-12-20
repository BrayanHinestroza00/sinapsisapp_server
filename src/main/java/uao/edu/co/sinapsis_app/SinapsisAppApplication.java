package uao.edu.co.sinapsis_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SinapsisAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinapsisAppApplication.class, args);
	}

}
