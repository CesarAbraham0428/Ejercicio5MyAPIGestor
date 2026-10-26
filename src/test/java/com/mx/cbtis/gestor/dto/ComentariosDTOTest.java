package com.mx.cbtis.gestor.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mx.cbtis.gestor.modelo.Comentarios;

class ComentariosDTOTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		ComentariosDTO d = new ComentariosDTO(1, "u1", 2, "Hola", "2025-05-01 10:00");

		assertEquals(1, d.getId_comentario());
		assertEquals("u1", d.getId_usuario());
		assertEquals(2, d.getId_tarea());
		assertEquals("Hola", d.getTexto_comentario());
		assertEquals("2025-05-01 10:00", d.getFecha_hora());
	}

	@Test
	void settersActualizanLosCampos() {
		ComentariosDTO d = new ComentariosDTO();
		assertNull(d.getId_comentario());

		d.setId_comentario(9);
		d.setId_usuario("u2");
		d.setId_tarea(8);
		d.setTexto_comentario("Adios");
		d.setFecha_hora("2025-06-01 12:00");

		assertEquals(9, d.getId_comentario());
		assertEquals("u2", d.getId_usuario());
		assertEquals(8, d.getId_tarea());
		assertEquals("Adios", d.getTexto_comentario());
		assertEquals("2025-06-01 12:00", d.getFecha_hora());
	}

	@Test
	void toEntityCopiaTodosLosCampos() {
		Comentarios e = new ComentariosDTO(1, "u1", 2, "Hola", "2025-05-01 10:00").toEntity();

		assertEquals(1, e.getId_comentario());
		assertEquals("u1", e.getId_usuario());
		assertEquals(2, e.getId_tarea());
		assertEquals("Hola", e.getTexto_comentario());
		assertEquals("2025-05-01 10:00", e.getFecha_hora());
	}
}
