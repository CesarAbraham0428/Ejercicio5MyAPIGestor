package com.mx.cbtis.gestor.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mx.cbtis.gestor.modelo.Tareas;

class TareasDTOTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		TareasDTO d = new TareasDTO(1, "Diseno", "Mockups", "2025-03-01", "Pendientes", "NUEVA", 5);

		assertEquals(1, d.getId_tarea());
		assertEquals("Diseno", d.getNombre());
		assertEquals("Mockups", d.getDescripcion());
		assertEquals("2025-03-01", d.getFecha_vencimiento());
		assertEquals("Pendientes", d.getLista());
		assertEquals("NUEVA", d.getEstado());
		assertEquals(5, d.getId_proyecto());
	}

	@Test
	void settersActualizanLosCampos() {
		TareasDTO d = new TareasDTO();
		assertNull(d.getId_tarea());

		d.setId_tarea(2);
		d.setNombre("Codigo");
		d.setDescripcion("Backend");
		d.setFecha_vencimiento("2025-04-01");
		d.setLista("En curso");
		d.setEstado("EN_PROGRESO");
		d.setId_proyecto(6);

		assertEquals(2, d.getId_tarea());
		assertEquals("Codigo", d.getNombre());
		assertEquals("Backend", d.getDescripcion());
		assertEquals("2025-04-01", d.getFecha_vencimiento());
		assertEquals("En curso", d.getLista());
		assertEquals("EN_PROGRESO", d.getEstado());
		assertEquals(6, d.getId_proyecto());
	}

	@Test
	void toEntityCopiaTodosLosCampos() {
		Tareas e = new TareasDTO(1, "Diseno", "Mockups", "2025-03-01", "Pendientes", "NUEVA", 5).toEntity();

		assertEquals(1, e.getId_tarea());
		assertEquals("Diseno", e.getNombre());
		assertEquals("Mockups", e.getDescripcion());
		assertEquals("2025-03-01", e.getFecha_vencimiento());
		assertEquals("Pendientes", e.getLista());
		assertEquals("NUEVA", e.getEstado());
		assertEquals(5, e.getId_proyecto());
	}
}
