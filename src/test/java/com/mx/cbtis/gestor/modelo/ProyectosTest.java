package com.mx.cbtis.gestor.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ProyectosTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		Proyectos p = new Proyectos(1, "Web", "Sitio", "2025-01-01", "2025-06-01", "ACTIVO", "azul");

		assertEquals(1, p.getId_proyecto());
		assertEquals("Web", p.getNombre());
		assertEquals("Sitio", p.getDescripcion());
		assertEquals("2025-01-01", p.getFecha_inicio());
		assertEquals("2025-06-01", p.getFecha_finalizacion());
		assertEquals("ACTIVO", p.getEstado_proyecto());
		assertEquals("azul", p.getFondo());
	}

	@Test
	void settersActualizanLosCampos() {
		Proyectos p = new Proyectos();
		assertNull(p.getId_proyecto());

		p.setId_proyecto(2);
		p.setNombre("App");
		p.setDescripcion("Movil");
		p.setFecha_inicio("2025-02-01");
		p.setFecha_finalizacion("2025-07-01");
		p.setEstado_proyecto("CERRADO");
		p.setFondo("rojo");

		assertEquals(2, p.getId_proyecto());
		assertEquals("App", p.getNombre());
		assertEquals("Movil", p.getDescripcion());
		assertEquals("2025-02-01", p.getFecha_inicio());
		assertEquals("2025-07-01", p.getFecha_finalizacion());
		assertEquals("CERRADO", p.getEstado_proyecto());
		assertEquals("rojo", p.getFondo());
	}
}
