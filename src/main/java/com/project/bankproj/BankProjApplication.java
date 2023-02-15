package com.project.bankproj;

import com.project.bankproj.entity.enums.AccountType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankProjApplication.class, args);
		int a = AccountType.CREDIT.getValue();
	}

}
