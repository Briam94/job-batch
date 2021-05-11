package com.carrito.compras.jobbatchcarritocompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.carrito.compras")
public class JobBatchCarritoComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBatchCarritoComprasApplication.class, args);
	}

}
