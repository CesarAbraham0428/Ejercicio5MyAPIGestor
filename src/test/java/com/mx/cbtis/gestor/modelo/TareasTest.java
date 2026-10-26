package com.mx.cbtis.gestor.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TareasTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		Tareas t = new Tareas(1, "Diseno", "Mockups", "2025-03-01", "Pendientes", "NUEVA", 5);

		assertEquals(1, t.getId_tarea());
		assertEquals("Diseno", t.getNombre());
		assertEquals("Mockups", t.getDescripcion());
		assertEquals("2025-03-01", t.getFecha_vencimiento());
		assertEquals("Pendientes", t.getLista());
		assertEquals("NUEVA", t.getEstado());
		assertEquals(5, t.getId_proyecto());
	}

	@Test
	void settersActualizanLosCampos() {
		Tareas t = new Tareas();
		assertNull(t.getId_tarea());

		t.setId_tarea(2);
		t.setNombre("Codigo");
		t.setDescripcion("Backend");
		t.setFecha_vencimiento("2025-04-01");
		t.setLista("En curso");
		t.setEstado("EN_PROGRESO");
		t.setId_proyecto(6);

		assertEquals(2, t.getId_tarea());
		assertEquals("Codigo", t.getNombre());
		assertEquals("Backend", t.getDescripcion());
		assertEquals("2025-04-01", t.getFecha_vencimiento());
		assertEquals("En curso", t.getLista());
		assertEquals("EN_PROGRESO", t.getEstado());
		assertEquals(6, t.getId_proyecto());
	}
}
