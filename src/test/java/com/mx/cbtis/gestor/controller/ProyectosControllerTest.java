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

import com.mx.cbtis.gestor.dto.ProyectosDTO;
import com.mx.cbtis.gestor.modelo.Proyectos;
import com.mx.cbtis.gestor.repository.ProtectosRepository;

@ExtendWith(MockitoExtension.class)
class ProyectosControllerTest {

	@Mock
	private ProtectosRepository proyectosRepository;

	@InjectMocks
	private ProyectosController controller;

	private static ProyectosDTO dto() {
		return new ProyectosDTO(3, "Proyecto A", "Descripción", "2026-10-01", "2026-12-01", "Activo", "azul");
	}

	private static void assertEntidadDelDto(Proyectos entidad) {
		assertEquals(3, entidad.getId_proyecto());
		assertEquals("Proyecto A", entidad.getNombre());
		assertEquals("Descripción", entidad.getDescripcion());
		assertEquals("2026-10-01", entidad.getFecha_inicio());
		assertEquals("2026-12-01", entidad.getFecha_finalizacion());
		assertEquals("Activo", entidad.getEstado_proyecto());
		assertEquals("azul", entidad.getFondo());
	}

	@Test
	void buscarProyectoCuandoExisteLoDevuelve() {
		Proyectos proyecto = new Proyectos(3, "Proyecto A", "Descripción", "2026-10-01", "2026-12-01", "Activo", "azul");
		when(proyectosRepository.findById(3)).thenReturn(Optional.of(proyecto));

		assertSame(proyecto, controller.getProyecto(3));
	}

	@Test
	void buscarProyectoCuandoNoExisteDevuelveUnProyectoVacio() {
		when(proyectosRepository.findById(99)).thenReturn(Optional.empty());

		Proyectos resultado = controller.getProyecto(99);

		assertNotNull(resultado);
		assertEquals(null, resultado.getId_proyecto());
	}

	@Test
	void agregarProyectoLoGuardaYDevuelveUno() {
		assertEquals(1, controller.addProyecto(dto()));

		ArgumentCaptor<Proyectos> captor = ArgumentCaptor.forClass(Proyectos.class);
		verify(proyectosRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void agregarProyectoNuloNoGuardaYDevuelveCero() {
		assertEquals(0, controller.addProyecto(null));
		verifyNoInteractions(proyectosRepository);
	}

	@Test
	void actualizarProyectoLoGuardaYDevuelveUno() {
		assertEquals(1, controller.updateProyecto(dto()));

		ArgumentCaptor<Proyectos> captor = ArgumentCaptor.forClass(Proyectos.class);
		verify(proyectosRepository).save(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void actualizarProyectoNuloNoGuardaYDevuelveCero() {
		assertEquals(0, controller.updateProyecto(null));
		verifyNoInteractions(proyectosRepository);
	}

	@Test
	void eliminarProyectoLoEliminaYDevuelveUno() {
		assertEquals(1, controller.deleteProyecto(dto()));

		ArgumentCaptor<Proyectos> captor = ArgumentCaptor.forClass(Proyectos.class);
		verify(proyectosRepository).delete(captor.capture());
		assertEntidadDelDto(captor.getValue());
	}

	@Test
	void eliminarProyectoNuloNoEliminaYDevuelveCero() {
		assertEquals(0, controller.deleteProyecto(null));
		verifyNoInteractions(proyectosRepository);
	}

	@Test
	void buscarProyectosDevuelveTodosLosRegistros() {
		Proyectos primero = new Proyectos(3, "Proyecto A", "Descripción A", "2026-10-01", "2026-12-01", "Activo", "azul");
		Proyectos segundo = new Proyectos(4, "Proyecto B", "Descripción B", "2026-10-02", "2026-12-02", "Pendiente", "verde");
		when(proyectosRepository.findAll()).thenReturn(Arrays.asList(primero, segundo));

		assertIterableEquals(Arrays.asList(primero, segundo), controller.getProyectos());
	}
}
