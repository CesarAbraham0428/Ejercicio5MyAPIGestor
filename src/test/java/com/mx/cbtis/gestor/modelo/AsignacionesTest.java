package com.mx.cbtis.gestor.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AsignacionesTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		Asignaciones a = new Asignaciones(1, 2, 3, "2025-05-01");

		assertEquals(1, a.getId_asignacion());
		assertEquals(2, a.getId_usuario());
		assertEquals(3, a.getId_tarea());
		assertEquals("2025-05-01", a.getFecha_asignacion());
	}

	@Test
	void settersActualizanLosCampos() {
		Asignaciones a = new Asignaciones();
		assertNull(a.getId_asignacion());

		a.setId_asignacion(10);
		a.setId_usuario(20);
		a.setId_tarea(30);
		a.setFecha_asignacion("2025-06-01");

		assertEquals(10, a.getId_asignacion());
		assertEquals(20, a.getId_usuario());
		assertEquals(30, a.getId_tarea());
		assertEquals("2025-06-01", a.getFecha_asignacion());
	}
}
