package com.mx.cbtis.gestor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mx.cbtis.gestor.dto.ComentariosDTO;
import com.mx.cbtis.gestor.modelo.Comentarios;
import com.mx.cbtis.gestor.repository.ComentariosRepository;

@ExtendWith(MockitoExtension.class)
class ComentariosControllerTest {

	@Mock
	private ComentariosRepository comentariosRepository;

	@InjectMocks
	private ComentariosController controller;

	private static ComentariosDTO dto() {
		return new ComentariosDTO(1, "7", 12, "Avance listo", "2026-10-01 10:00");
	}

	private static void assertEntidadDelDto(Comentarios entidad) {
		assertEquals(1, entidad.getId_comentario());
		assertEquals("7", entidad.getId_usuario());
		assertEquals(12, entidad.getId_tarea());
		assertEquals("Avance listo", entidad.getTexto_comentario());
		assertEquals("2026-10-01 10:00", entidad.getFecha_hora());
	}

	@Test
	void buscarComentariosPorTareaUsaElIdDeLaTarea() {
		ComentariosDTO filtro = new ComentariosDTO(0, null, 12, null, null);
		Comentarios comentario = new Comentarios(1, "7", 12, "Avance listo", "2026-10-01 10:00");
		when(comentariosRepository.findByTarea(12)).thenReturn(Collections.singletonList(comentario));

		assertIterableEquals(Collections.singletonList(comentario), controller.getComentario(filtro));
		verify(comentariosRepository).findByTarea(12);
	}

	@Test
	void buscarComentarioCuandoExisteLoDevuelve() {
		Comentarios comentario = new Comentarios(1, "7", 12, "Avance listo", "2026-10-01 10:00");
		when(comentariosRepository.findById(1)).thenReturn(Optional.of(comentario));

		assertSame(comentario, controller.getComentario(1));
	}

	@Test
	void buscarComentarioCuandoNoExisteDevuelveUnComentarioVacio() {
		when(comentariosRepository.findById(99)).thenReturn(Optional.empty());

		Comentarios resultado = controller.getComentario(99);

		assertNotNull(resultado);
		assertEquals(null, resultado.getId_comentario());
	}

	@Test
	void agregarComentarioLoGuardaYDevuelveUno() {
		assertEquals(1, controller.addComentario(dto()));

		ArgumentCaptor<Comentarios> captor = ArgumentCaptor.forClass(Comentarios.class);
		verify(comentariosRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void agregarComentarioNuloNoGuardaYDevuelveCero() {
		assertEquals(0, controller.addComentario(null));
		verifyNoInteractions(comentariosRepository);
	}

	@Test
	void actualizarComentarioLoGuardaYDevuelveUno() {
		assertEquals(1, controller.updateComentario(dto()));

		ArgumentCaptor<Comentarios> captor = ArgumentCaptor.forClass(Comentarios.class);
		verify(comentariosRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void actualizarComentarioNuloNoGuardaYDevuelveCero() {
		assertEquals(0, controller.updateComentario(null));
		verifyNoInteractions(comentariosRepository);
	}

	@Test
	void eliminarComentarioLoEliminaYDevuelveUno() {
		assertEquals(1, controller.deleteComentario(dto()));

		ArgumentCaptor<Comentarios> captor = ArgumentCaptor.forClass(Comentarios.class);
		verify(comentariosRepository).delete(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void eliminarComentarioNuloNoEliminaYDevuelveCero() {
		assertEquals(0, controller.deleteComentario(null));
		verifyNoInteractions(comentariosRepository);
	}

	@Test
	void buscarComentariosDevuelveTodosLosRegistros() {
		Comentarios primero = new Comentarios(1, "7", 12, "Uno", "2026-10-01 10:00");
		Comentarios segundo = new Comentarios(2, "8", 13, "Dos", "2026-10-02 10:00");
		when(comentariosRepository.findAll()).thenReturn(Arrays.asList(primero, segundo));

		assertIterableEquals(Arrays.asList(primero, segundo), controller.getComentarios());
	}
}
