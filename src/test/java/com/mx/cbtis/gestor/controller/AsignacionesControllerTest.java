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

import com.mx.cbtis.gestor.dto.AsignacionesDTO;
import com.mx.cbtis.gestor.modelo.Asignaciones;
import com.mx.cbtis.gestor.repository.AsignacionesRepository;

@ExtendWith(MockitoExtension.class)
class AsignacionesControllerTest {

	@Mock
	private AsignacionesRepository asignacionesRepository;

	@InjectMocks
	private AsignacionesController controller;

	private static AsignacionesDTO dto() {
		return new AsignacionesDTO(1, 7, 12, "2026-10-01");
	}

	private static void assertEntidadDelDto(Asignaciones entidad) {
		assertEquals(1, entidad.getId_asignacion());
		assertEquals(7, entidad.getId_usuario());
		assertEquals(12, entidad.getId_tarea());
		assertEquals("2026-10-01", entidad.getFecha_asignacion());
	}

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
		assertEquals(1, controller.addAsignacion(dto()));

		ArgumentCaptor<Asignaciones> captor = ArgumentCaptor.forClass(Asignaciones.class);
		verify(asignacionesRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void agregarAsignacionNulaNoGuardaYDevuelveCero() {
		assertEquals(0, controller.addAsignacion(null));
		verifyNoInteractions(asignacionesRepository);
	}

	@Test
	void actualizarAsignacionLaGuardaYDevuelveUno() {
		assertEquals(1, controller.updateAsignacion(dto()));

		ArgumentCaptor<Asignaciones> captor = ArgumentCaptor.forClass(Asignaciones.class);
		verify(asignacionesRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void actualizarAsignacionNulaNoGuardaYDevuelveCero() {
		assertEquals(0, controller.updateAsignacion(null));
		verifyNoInteractions(asignacionesRepository);
	}

	@Test
	void eliminarAsignacionLaEliminaYDevuelveUno() {
		assertEquals(1, controller.deleteAsignacion(dto()));

		ArgumentCaptor<Asignaciones> captor = ArgumentCaptor.forClass(Asignaciones.class);
		verify(asignacionesRepository).delete(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void eliminarAsignacionNulaNoEliminaYDevuelveCero() {
		assertEquals(0, controller.deleteAsignacion(null));
		verifyNoInteractions(asignacionesRepository);
	}

	@Test
	void buscarAsignacionesDevuelveTodosLosRegistros() {
		Asignaciones primera = new Asignaciones(1, 7, 12, "2026-10-01");
		Asignaciones segunda = new Asignaciones(2, 8, 13, "2026-10-02");
		when(asignacionesRepository.findAll()).thenReturn(Arrays.asList(primera, segunda));

		assertIterableEquals(Arrays.asList(primera, segunda), controller.getAsignaciones());
	}
}
