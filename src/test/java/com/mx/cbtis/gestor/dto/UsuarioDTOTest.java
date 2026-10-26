package com.mx.cbtis.gestor.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mx.cbtis.gestor.modelo.Usuario;

class UsuarioDTOTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		UsuarioDTO d = new UsuarioDTO(1, "Ana", "ana@ejemplo.com", "secreto", "ADMIN", "ana.png");

		assertEquals(1, d.getId_usuario());
		assertEquals("Ana", d.getNombre());
		assertEquals("ana@ejemplo.com", d.getCorreo_electronico());
		assertEquals("secreto", d.getContrasena());
		assertEquals("ADMIN", d.getRol());
		assertEquals("ana.png", d.getImagen_usuario());
	}

	@Test
	void settersActualizanLosCampos() {
		UsuarioDTO d = new UsuarioDTO();
		assertNull(d.getId_usuario());

		d.setId_usuario(2);
		d.setNombre("Luis");
		d.setCorreo_electronico("luis@ejemplo.com");
		d.setContrasena("clave");
		d.setRol("USER");
		d.setImagen_usuario("luis.png");

		assertEquals(2, d.getId_usuario());
		assertEquals("Luis", d.getNombre());
		assertEquals("luis@ejemplo.com", d.getCorreo_electronico());
		assertEquals("clave", d.getContrasena());
		assertEquals("USER", d.getRol());
		assertEquals("luis.png", d.getImagen_usuario());
	}

	@Test
	void toEntityCopiaTodosLosCampos() {
		Usuario e = new UsuarioDTO(1, "Ana", "ana@ejemplo.com", "secreto", "ADMIN", "ana.png").toEntity();

		assertEquals(1, e.getId_usuario());
		assertEquals("Ana", e.getNombre());
		assertEquals("ana@ejemplo.com", e.getCorreo_electronico());
		assertEquals("secreto", e.getContrasena());
		assertEquals("ADMIN", e.getRol());
		assertEquals("ana.png", e.getImagen_usuario());
	}
}
