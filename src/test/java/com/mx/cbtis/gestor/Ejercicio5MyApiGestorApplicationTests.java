package com.mx.cbtis.gestor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ejercicio5MyApiGestorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainArrancaLaAplicacion() {
		String[] args = { "--server.port=0", "--spring.main.web-application-type=none" };

		assertDoesNotThrow(() -> Ejercicio5MyApiGestorApplication.main(args));
	}

}
