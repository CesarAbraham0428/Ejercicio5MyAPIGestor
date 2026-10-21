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

import com.mx.cbtis.gestor.modelo.Proyectos;
import com.mx.cbtis.gestor.repository.ProtectosRepository;

@ExtendWith(MockitoExtension.class)
class ProyectosControllerTest {

	@Mock
	private ProtectosRepository proyectosRepository;

	@InjectMocks
	private ProyectosController controller;

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
		Proyectos proyecto = new Proyectos(3, "Proyecto A", "Descripción", "2026-10-01", "2026-12-01", "Activo", "azul");

		assertEquals(1, controller.addProyecto(proyecto));
		verify(proyectosRepository).save(proyecto);
	}

	@Test
	void actualizarProyectoLoGuardaYDevuelveUno() {
		Proyectos proyecto = new Proyectos(3, "Proyecto A", "Descripción", "2026-10-01", "2026-12-01", "Activo", "azul");

		assertEquals(1, controller.updateProyecto(proyecto));
		verify(proyectosRepository).save(proyecto);
	}

	@Test
	void eliminarProyectoLoEliminaYDevuelveUno() {
		Proyectos proyecto = new Proyectos(3, "Proyecto A", "Descripción", "2026-10-01", "2026-12-01", "Activo", "azul");

		assertEquals(1, controller.deleteProyecto(proyecto));
		verify(proyectosRepository).delete(proyecto);
	}

	@Test
	void buscarProyectosDevuelveTodosLosRegistros() {
		Proyectos primero = new Proyectos(3, "Proyecto A", "Descripción A", "2026-10-01", "2026-12-01", "Activo", "azul");
		Proyectos segundo = new Proyectos(4, "Proyecto B", "Descripción B", "2026-10-02", "2026-12-02", "Pendiente", "verde");
		when(proyectosRepository.findAll()).thenReturn(Arrays.asList(primero, segundo));

		assertIterableEquals(Arrays.asList(primero, segundo), controller.getProyectos());
	}
}
