package com.hundsun.accountingsystem;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableWebSecurity
@MapperScan("com.hundsun.accountingsystem.Global.mapper")//扫描多个包
@ComponentScan(basePackages ="com.hundsun.accountingsystem")//扫描插件
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication
public class AccountingSystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AccountingSystemApplication.class, args);
	}
}
