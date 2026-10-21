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

import com.mx.cbtis.gestor.modelo.Asignaciones;
import com.mx.cbtis.gestor.repository.AsignacionesRepository;

@ExtendWith(MockitoExtension.class)
class AsignacionesControllerTest {

	@Mock
	private AsignacionesRepository asignacionesRepository;

	@InjectMocks
	private AsignacionesController controller;

	@Test
	void buscarAsignacionCuandoExisteLaDevuelve() {
		Asignaciones asignacion = new Asignaciones(1, 7, 12, "2026-10-01");
		when(asignacionesRepository.findById(1)).thenReturn(Optional.of(asignacion));

		Asignaciones resultado = controller.getAsignacion(1);

		assertSame(asignacion, resultado);
	}

	@Test
	void buscarAsignacionCuandoNoExisteDevuelveUnaAsignacionVacia() {
		when(asignacionesRepository.findById(99)).thenReturn(Optional.empty());

		Asignaciones resultado = controller.getAsignacion(99);

		assertNotNull(resultado);
		assertEquals(null, resultado.getId_asignacion());
	}

	@Test
	void agregarAsignacionLaGuardaYDevuelveUno() {
		Asignaciones asignacion = new Asignaciones(1, 7, 12, "2026-10-01");

		assertEquals(1, controller.addAsignacion(asignacion));
		verify(asignacionesRepository).save(asignacion);
	}

	@Test
	void actualizarAsignacionLaGuardaYDevuelveUno() {
		Asignaciones asignacion = new Asignaciones(1, 7, 12, "2026-10-01");

		assertEquals(1, controller.updateAsignacion(asignacion));
		verify(asignacionesRepository).save(asignacion);
	}

	@Test
	void eliminarAsignacionLaEliminaYDevuelveUno() {
		Asignaciones asignacion = new Asignaciones(1, 7, 12, "2026-10-01");

		assertEquals(1, controller.deleteAsignacion(asignacion));
		verify(asignacionesRepository).delete(asignacion);
	}

	@Test
	void buscarAsignacionesDevuelveTodosLosRegistros() {
		Asignaciones primera = new Asignaciones(1, 7, 12, "2026-10-01");
		Asignaciones segunda = new Asignaciones(2, 8, 13, "2026-10-02");
		when(asignacionesRepository.findAll()).thenReturn(Arrays.asList(primera, segunda));

		assertIterableEquals(Arrays.asList(primera, segunda), controller.getAsignaciones());
	}
}
