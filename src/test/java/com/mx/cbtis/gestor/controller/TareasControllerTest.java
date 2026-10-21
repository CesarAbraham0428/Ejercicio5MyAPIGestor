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

import com.mx.cbtis.gestor.modelo.Tareas;
import com.mx.cbtis.gestor.repository.TareasRepository;

@ExtendWith(MockitoExtension.class)
class TareasControllerTest {

	@Mock
	private TareasRepository tareasRepository;

	@InjectMocks
	private TareasController controller;

	@Test
	void buscarTareasPorProyectoUsaElIdDelProyecto() {
		Tareas filtro = new Tareas(null, null, null, null, null, null, 5);
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
		Tareas tarea = new Tareas(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);

		assertEquals(1, controller.addTareas(tarea));
		verify(tareasRepository).save(tarea);
	}

	@Test
	void actualizarTareaLaGuardaYDevuelveUno() {
		Tareas tarea = new Tareas(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);

		assertEquals(1, controller.updateTareas(tarea));
		verify(tareasRepository).save(tarea);
	}

	@Test
	void eliminarTareaLaEliminaYDevuelveUno() {
		Tareas tarea = new Tareas(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);

		assertEquals(1, controller.deleteTareas(tarea));
		verify(tareasRepository).delete(tarea);
	}

	@Test
	void buscarTareasDevuelveTodosLosRegistros() {
		Tareas primera = new Tareas(10, "Implementar API", "Crear endpoints", "2026-10-20", "Pendiente", "Abierta", 5);
		Tareas segunda = new Tareas(11, "Escribir documentación", "Documentar API", "2026-10-22", "En proceso", "Abierta", 5);
		when(tareasRepository.findAll()).thenReturn(Arrays.asList(primera, segunda));

		assertIterableEquals(Arrays.asList(primera, segunda), controller.getTareas());
	}
}
