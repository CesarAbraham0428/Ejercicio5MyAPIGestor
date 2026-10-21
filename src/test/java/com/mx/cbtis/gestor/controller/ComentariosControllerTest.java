package com.mx.cbtis.gestor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mx.cbtis.gestor.modelo.Comentarios;
import com.mx.cbtis.gestor.repository.ComentariosRepository;

@ExtendWith(MockitoExtension.class)
class ComentariosControllerTest {

	@Mock
	private ComentariosRepository comentariosRepository;

	@InjectMocks
	private ComentariosController controller;

	@Test
	void buscarComentariosPorTareaUsaElIdDeLaTarea() {
		Comentarios filtro = new Comentarios(0, null, 12, null, null);
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
		Comentarios comentario = new Comentarios(1, "7", 12, "Avance listo", "2026-10-01 10:00");

		assertEquals(1, controller.addComentario(comentario));
		verify(comentariosRepository).save(comentario);
	}

	@Test
	void actualizarComentarioLoGuardaYDevuelveUno() {
		Comentarios comentario = new Comentarios(1, "7", 12, "Avance listo", "2026-10-01 10:00");

		assertEquals(1, controller.updateComentario(comentario));
		verify(comentariosRepository).save(comentario);
	}

	@Test
	void eliminarComentarioLoEliminaYDevuelveUno() {
		Comentarios comentario = new Comentarios(1, "7", 12, "Avance listo", "2026-10-01 10:00");

		assertEquals(1, controller.deleteComentario(comentario));
		verify(comentariosRepository).delete(comentario);
	}

	@Test
	void buscarComentariosDevuelveTodosLosRegistros() {
		Comentarios primero = new Comentarios(1, "7", 12, "Uno", "2026-10-01 10:00");
		Comentarios segundo = new Comentarios(2, "8", 13, "Dos", "2026-10-02 10:00");
		when(comentariosRepository.findAll()).thenReturn(Arrays.asList(primero, segundo));

		assertIterableEquals(Arrays.asList(primero, segundo), controller.getComentarios());
	}
}
