package com.mx.cbtis.gestor.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UsuarioTest {

	@Test
	void constructorConParametrosAsignaTodosLosCampos() {
		Usuario u = new Usuario(1, "Ana", "ana@ejemplo.com", "secreto", "ADMIN", "ana.png");

		assertEquals(1, u.getId_usuario());
		assertEquals("Ana", u.getNombre());
		assertEquals("ana@ejemplo.com", u.getCorreo_electronico());
		assertEquals("secreto", u.getContrasena());
		assertEquals("ADMIN", u.getRol());
		assertEquals("ana.png", u.getImagen_usuario());
	}

	@Test
	void settersActualizanLosCampos() {
		Usuario u = new Usuario();
		assertNull(u.getId_usuario());

		u.setId_usuario(2);
		u.setNombre("Luis");
		u.setCorreo_electronico("luis@ejemplo.com");
		u.setContrasena("clave");
		u.setRol("USER");
		u.setImagen_usuario("luis.png");

		assertEquals(2, u.getId_usuario());
		assertEquals("Luis", u.getNombre());
		assertEquals("luis@ejemplo.com", u.getCorreo_electronico());
		assertEquals("clave", u.getContrasena());
		assertEquals("USER", u.getRol());
		assertEquals("luis.png", u.getImagen_usuario());
	}
}
