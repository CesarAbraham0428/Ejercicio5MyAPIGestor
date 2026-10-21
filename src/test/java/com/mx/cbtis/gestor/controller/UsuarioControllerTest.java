package com.mx.cbtis.gestor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mx.cbtis.gestor.modelo.Usuario;
import com.mx.cbtis.gestor.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioController controller;

	@Test
	void loginConCredencialesValidasDevuelveElUsuario() {
		Usuario credenciales = new Usuario(null, null, "usuario@ejemplo.com", "secreto", null, null);
		Usuario usuario = new Usuario(7, "Ana", "usuario@ejemplo.com", "secreto", "ADMIN", "ana.png");
		when(usuarioRepository.findByCorreoPass("usuario@ejemplo.com", "secreto"))
				.thenReturn(Optional.of(usuario));

		assertSame(usuario, controller.getUsuarioLogin(credenciales));
		verify(usuarioRepository).findByCorreoPass("usuario@ejemplo.com", "secreto");
	}

	@Test
	void loginConCredencialesInvalidasDevuelveUsuarioConNombreSentinela() {
		Usuario credenciales = new Usuario(null, null, "usuario@ejemplo.com", "incorrecta", null, null);
		when(usuarioRepository.findByCorreoPass("usuario@ejemplo.com", "incorrecta"))
				.thenReturn(Optional.empty());

		Usuario resultado = controller.getUsuarioLogin(credenciales);

		assertNotNull(resultado);
		assertEquals("000", resultado.getNombre());
	}

	@Test
	void buscarUsuarioCuandoExisteLoDevuelve() {
		Usuario usuario = new Usuario(7, "Ana", "usuario@ejemplo.com", "secreto", "ADMIN", "ana.png");
		when(usuarioRepository.findById(7)).thenReturn(Optional.of(usuario));

		assertSame(usuario, controller.getUsuario(7));
	}

	@Test
	void buscarUsuarioCuandoNoExisteDevuelveUnUsuarioVacio() {
		when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

		Usuario resultado = controller.getUsuario(99);

		assertNotNull(resultado);
		assertEquals(null, resultado.getId_usuario());
	}

	@Test
	void agregarUsuarioLoGuardaYDevuelveUno() {
		Usuario usuario = new Usuario(7, "Ana", "usuario@ejemplo.com", "secreto", "ADMIN", "ana.png");

		assertEquals(1, controller.addUsuario(usuario));
		verify(usuarioRepository).save(usuario);
	}

	@Test
	void actualizarUsuarioLoGuardaYDevuelveUno() {
		Usuario usuario = new Usuario(7, "Ana", "usuario@ejemplo.com", "secreto", "ADMIN", "ana.png");

		assertEquals(1, controller.updateUsuario(usuario));
		verify(usuarioRepository).save(usuario);
	}

	@Test
	void eliminarUsuarioLoEliminaYDevuelveUno() {
		Usuario usuario = new Usuario(7, "Ana", "usuario@ejemplo.com", "secreto", "ADMIN", "ana.png");

		assertEquals(1, controller.deleteUsuario(usuario));
		verify(usuarioRepository).delete(usuario);
	}

	@Test
	void buscarUsuariosDevuelveTodosLosRegistros() {
		Usuario primero = new Usuario(7, "Ana", "ana@ejemplo.com", "secreto", "ADMIN", "ana.png");
		Usuario segundo = new Usuario(8, "Luis", "luis@ejemplo.com", "secreto", "USER", "luis.png");
		when(usuarioRepository.findAll()).thenReturn(Arrays.asList(primero, segundo));

		assertIterableEquals(Arrays.asList(primero, segundo), controller.getUsuarios());
	}
}
