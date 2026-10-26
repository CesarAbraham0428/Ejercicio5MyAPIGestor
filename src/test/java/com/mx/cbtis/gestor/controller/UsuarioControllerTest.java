package com.mx.cbtis.gestor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mx.cbtis.gestor.dto.UsuarioDTO;
import com.mx.cbtis.gestor.modelo.Usuario;
import com.mx.cbtis.gestor.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioController controller;

	private static UsuarioDTO dto() {
		return new UsuarioDTO(7, "Ana", "usuario@ejemplo.com", "secreto", "ADMIN", "ana.png");
	}

	private static void assertEntidadDelDto(Usuario entidad) {
		assertEquals(7, entidad.getId_usuario());
		assertEquals("Ana", entidad.getNombre());
		assertEquals("usuario@ejemplo.com", entidad.getCorreo_electronico());
		assertEquals("secreto", entidad.getContrasena());
		assertEquals("ADMIN", entidad.getRol());
		assertEquals("ana.png", entidad.getImagen_usuario());
	}

	@Test
	void loginConCredencialesValidasDevuelveElUsuario() {
		UsuarioDTO credenciales = new UsuarioDTO(null, null, "usuario@ejemplo.com", "secreto", null, null);
		Usuario usuario = new Usuario(7, "Ana", "usuario@ejemplo.com", "secreto", "ADMIN", "ana.png");
		when(usuarioRepository.findByCorreoPass("usuario@ejemplo.com", "secreto"))
				.thenReturn(Optional.of(usuario));

		assertSame(usuario, controller.getUsuarioLogin(credenciales));
		verify(usuarioRepository).findByCorreoPass("usuario@ejemplo.com", "secreto");
	}

	@Test
	void loginConCredencialesInvalidasDevuelveUsuarioConNombreSentinela() {
		UsuarioDTO credenciales = new UsuarioDTO(null, null, "usuario@ejemplo.com", "incorrecta", null, null);
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
		assertEquals(1, controller.addUsuario(dto()));

		ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
		verify(usuarioRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void agregarUsuarioNuloNoGuardaYDevuelveCero() {
		assertEquals(0, controller.addUsuario(null));
		verifyNoInteractions(usuarioRepository);
	}

	@Test
	void actualizarUsuarioLoGuardaYDevuelveUno() {
		assertEquals(1, controller.updateUsuario(dto()));

		ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
		verify(usuarioRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void actualizarUsuarioNuloNoGuardaYDevuelveCero() {
		assertEquals(0, controller.updateUsuario(null));
		verifyNoInteractions(usuarioRepository);
	}

	@Test
	void eliminarUsuarioLoEliminaYDevuelveUno() {
		assertEquals(1, controller.deleteUsuario(dto()));

		ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
		verify(usuarioRepository).delete(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void eliminarUsuarioNuloNoEliminaYDevuelveCero() {
		assertEquals(0, controller.deleteUsuario(null));
		verifyNoInteractions(usuarioRepository);
	}

	@Test
	void buscarUsuariosDevuelveTodosLosRegistros() {
		Usuario primero = new Usuario(7, "Ana", "ana@ejemplo.com", "secreto", "ADMIN", "ana.png");
		Usuario segundo = new Usuario(8, "Luis", "luis@ejemplo.com", "secreto", "USER", "luis.png");
		when(usuarioRepository.findAll()).thenReturn(Arrays.asList(primero, segundo));

		assertIterableEquals(Arrays.asList(primero, segundo), controller.getUsuarios());
	}
}
