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

import com.mx.cbtis.gestor.dto.TareasDTO;
import com.mx.cbtis.gestor.modelo.Tareas;
import com.mx.cbtis.gestor.repository.TareasRepository;

@ExtendWith(MockitoExtension.class)
class TareasControllerTest {

	@Mock
	private TareasRepository tareasRepository;

	@InjectMocks
	private TareasController controller;

	private static TareasDTO dto() {
		return new TareasDTO(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);
	}

	private static void assertEntidadDelDto(Tareas entidad) {
		assertEquals(10, entidad.getId_tarea());
		assertEquals("Implementar API", entidad.getNombre());
		assertEquals("Crear endpoints", entidad.getDescripcion());
		assertEquals("2026-10-20", entidad.getFecha_vencimiento());
		assertEquals("Pendiente", entidad.getLista());
		assertEquals("Abierta", entidad.getEstado());
		assertEquals(5, entidad.getId_proyecto());
	}

	@Test
	void buscarTareasPorProyectoUsaElIdDelProyecto() {
		TareasDTO filtro = new TareasDTO(null, null, null, null, null, null, 5);
		Tareas tarea = new Tareas(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);
		when(tareasRepository.findByIdProyecto(5)).thenReturn(Collections.singletonList(tarea));

		assertIterableEquals(Collections.singletonList(tarea), controller.getTarea(filtro));
		verify(tareasRepository).findByIdProyecto(5);
	}

	@Test
	void buscarTareaCuandoExisteLaDevuelve() {
		Tareas tarea = new Tareas(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);
		when(tareasRepository.findById(10)).thenReturn(Optional.of(tarea));

		assertSame(tarea, controller.getTarea(10));
	}

	@Test
	void buscarTareaCuandoNoExisteDevuelveUnaTareaVacia() {
		when(tareasRepository.findById(99)).thenReturn(Optional.empty());

		Tareas resultado = controller.getTarea(99);

		assertNotNull(resultado);
		assertEquals(null, resultado.getId_tarea());
	}

	@Test
	void agregarTareaLaGuardaYDevuelveUno() {
		assertEquals(1, controller.addTareas(dto()));

		ArgumentCaptor<Tareas> captor = ArgumentCaptor.forClass(Tareas.class);
		verify(tareasRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void agregarTareaNulaNoGuardaYDevuelveCero() {
		assertEquals(0, controller.addTareas(null));
		verifyNoInteractions(tareasRepository);
	}

	@Test
	void actualizarTareaLaGuardaYDevuelveUno() {
		assertEquals(1, controller.updateTareas(dto()));

		ArgumentCaptor<Tareas> captor = ArgumentCaptor.forClass(Tareas.class);
		verify(tareasRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void actualizarTareaNulaNoGuardaYDevuelveCero() {
		assertEquals(0, controller.updateTareas(null));
		verifyNoInteractions(tareasRepository);
	}

	@Test
	void eliminarTareaLaEliminaYDevuelveUno() {
		assertEquals(1, controller.deleteTareas(dto()));

		ArgumentCaptor<Tareas> captor = ArgumentCaptor.forClass(Tareas.class);
		verify(tareasRepository).delete(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void eliminarTareaNulaNoEliminaYDevuelveCero() {
		assertEquals(0, controller.deleteTareas(null));
		verifyNoInteractions(tareasRepository);
	}

	@Test
	void buscarTareasDevuelveTodosLosRegistros() {
		Tareas primera = new Tareas(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);
		Tareas segunda = new Tareas(11, "Escribir documentación", "Documentar API", "2026-10-22", "En proceso", "Abierta", 5);
		when(tareasRepository.findAll()).thenReturn(Arrays.asList(primera, segunda));

		assertIterableEquals(Arrays.asList(primera, segunda), controller.getTareas());
	}
}
