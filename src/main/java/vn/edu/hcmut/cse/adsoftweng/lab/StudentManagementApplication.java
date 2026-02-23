package vn.edu.hcmut.cse.adsoftweng.lab;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		// ÉP timezone trước khi Spring khởi động
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        System.out.println("JVM Timezone = " + TimeZone.getDefault());
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
