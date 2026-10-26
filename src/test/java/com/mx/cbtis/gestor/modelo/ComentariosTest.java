package com.mx.cbtis.gestor.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ComentariosTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		Comentarios c = new Comentarios(1, "u1", 2, "Hola", "2025-05-01 10:00");

		assertEquals(1, c.getId_comentario());
		assertEquals("u1", c.getId_usuario());
		assertEquals(2, c.getId_tarea());
		assertEquals("Hola", c.getTexto_comentario());
		assertEquals("2025-05-01 10:00", c.getFecha_hora());
	}

	@Test
	void settersActualizanLosCampos() {
		Comentarios c = new Comentarios();
		assertNull(c.getId_comentario());

		c.setId_comentario(9);
		c.setId_usuario("u2");
		c.setId_tarea(8);
		c.setTexto_comentario("Adios");
		c.setFecha_hora("2025-06-01 12:00");

		assertEquals(9, c.getId_comentario());
		assertEquals("u2", c.getId_usuario());
		assertEquals(8, c.getId_tarea());
		assertEquals("Adios", c.getTexto_comentario());
		assertEquals("2025-06-01 12:00", c.getFecha_hora());
	}
}
