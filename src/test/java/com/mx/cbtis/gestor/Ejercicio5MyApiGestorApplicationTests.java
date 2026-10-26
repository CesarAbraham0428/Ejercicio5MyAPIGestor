package com.mx.cbtis.gestor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ejercicio5MyApiGestorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainArrancaLaAplicacion() {
		Ejercicio5MyApiGestorApplication.main(new String[] { "--server.port=0", "--spring.main.web-application-type=none" });
	}

}
