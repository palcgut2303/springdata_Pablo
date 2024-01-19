package com.example.springdata;

import com.example.springdata.Repositories.PedidoRepository;
import com.example.springdata.Repositories.ProductRepository;
import com.example.springdata.Repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringdataApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringdataApplication.class, args);

		var usuarioRepo = context.getBean(UserRepository.class);

		var pedidosRepo = context.getBean(PedidoRepository.class);

		var productRepo = context.getBean(ProductRepository.class);

	}

}
