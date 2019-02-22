package com.hundsun.accountingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class AccountingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountingSystemApplication.class, args);
	}

}
