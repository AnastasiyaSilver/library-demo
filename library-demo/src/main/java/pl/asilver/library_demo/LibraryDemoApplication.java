package pl.asilver.library_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class LibraryDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryDemoApplication.class, args);
	}

}
