package com.mx.cbtis.gestor.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mx.cbtis.gestor.modelo.Proyectos;

class ProyectosDTOTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		ProyectosDTO d = new ProyectosDTO(1, "Web", "Sitio", "2025-01-01", "2025-06-01", "ACTIVO", "azul");

		assertEquals(1, d.getId_proyecto());
		assertEquals("Web", d.getNombre());
		assertEquals("Sitio", d.getDescripcion());
		assertEquals("2025-01-01", d.getFecha_inicio());
		assertEquals("2025-06-01", d.getFecha_finalizacion());
		assertEquals("ACTIVO", d.getEstado_proyecto());
		assertEquals("azul", d.getFondo());
	}

	@Test
	void settersActualizanLosCampos() {
		ProyectosDTO d = new ProyectosDTO();
		assertNull(d.getId_proyecto());

		d.setId_proyecto(2);
		d.setNombre("App");
		d.setDescripcion("Movil");
		d.setFecha_inicio("2025-02-01");
		d.setFecha_finalizacion("2025-07-01");
		d.setEstado_proyecto("CERRADO");
		d.setFondo("rojo");

		assertEquals(2, d.getId_proyecto());
		assertEquals("App", d.getNombre());
		assertEquals("Movil", d.getDescripcion());
		assertEquals("2025-02-01", d.getFecha_inicio());
		assertEquals("2025-07-01", d.getFecha_finalizacion());
		assertEquals("CERRADO", d.getEstado_proyecto());
		assertEquals("rojo", d.getFondo());
	}

	@Test
	void toEntityCopiaTodosLosCampos() {
		Proyectos e = new ProyectosDTO(1, "Web", "Sitio", "2025-01-01", "2025-06-01", "ACTIVO", "azul").toEntity();

		assertEquals(1, e.getId_proyecto());
		assertEquals("Web", e.getNombre());
		assertEquals("Sitio", e.getDescripcion());
		assertEquals("2025-01-01", e.getFecha_inicio());
		assertEquals("2025-06-01", e.getFecha_finalizacion());
		assertEquals("ACTIVO", e.getEstado_proyecto());
		assertEquals("azul", e.getFondo());
	}
}
