package com.mx.cbtis.gestor.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mx.cbtis.gestor.modelo.Asignaciones;

class AsignacionesDTOTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		AsignacionesDTO d = new AsignacionesDTO(1, 2, 3, "2025-05-01");

		assertEquals(1, d.getId_asignacion());
		assertEquals(2, d.getId_usuario());
		assertEquals(3, d.getId_tarea());
		assertEquals("2025-05-01", d.getFecha_asignacion());
	}

	@Test
	void settersActualizanLosCampos() {
		AsignacionesDTO d = new AsignacionesDTO();
		assertNull(d.getId_asignacion());

		d.setId_asignacion(10);
		d.setId_usuario(20);
		d.setId_tarea(30);
		d.setFecha_asignacion("2025-06-01");

		assertEquals(10, d.getId_asignacion());
		assertEquals(20, d.getId_usuario());
		assertEquals(30, d.getId_tarea());
		assertEquals("2025-06-01", d.getFecha_asignacion());
	}

	@Test
	void toEntityCopiaTodosLosCampos() {
		Asignaciones e = new AsignacionesDTO(1, 2, 3, "2025-05-01").toEntity();

		assertEquals(1, e.getId_asignacion());
		assertEquals(2, e.getId_usuario());
		assertEquals(3, e.getId_tarea());
		assertEquals("2025-05-01", e.getFecha_asignacion());
	}
}
