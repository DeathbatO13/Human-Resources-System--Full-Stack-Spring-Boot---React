package gm.rh.service;

import gm.rh.modelo.Empleado;
import gm.rh.repository.EmpleadoRepostory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link EmpleadoServicio}.
 *
 * Uses Mockito to mock the {@link EmpleadoRepository} and test the service layer
 * in isolation. {@code @ExtendWith(MockitoExtension.class)} enables lightweight
 * Mockito annotations without requiring Spring context.
 */
@ExtendWith(MockitoExtension.class)
public class EmpleadoServicioTest {

    @Mock
    private EmpleadoRepostory empleadoRepostory;

    @InjectMocks
    private EmpleadoServicio empleadoServicio;

    /**
     * Verifies that {@link EmpleadoServicio#listarEmpleados()} returns the full list
     * of employees by delegating to {@link EmpleadoRepository#findAll()}.
     *
     * Mocks the repository to return a list with two employees and asserts that
     * the service returns a list of the same size. Also verifies that the repository
     * method was called exactly once.
     */
    @Test
    void listarEmpleados_debeRetornarLista(){

        Empleado e1 = new Empleado(1, "Juan", "IT", 3000.0);
        Empleado e2 = new Empleado(2,"Ana", "HR", 2500.0);

        when(empleadoRepostory.findAll()).thenReturn(List.of(e1, e2));

        List<Empleado> resultado = empleadoServicio.listarEmpleados();

        assertThat(resultado).hasSize(2);
        verify(empleadoRepostory).findAll();

    }

    /**
     * Tests {@link EmpleadoServicio#buscarEmpleadoPorId(int)} when the employee exists.
     *
     * Configures the mock repository to return an {@link Optional} containing the employee
     * with the requested ID. Verifies that the service returns the correct non-null employee
     * with the expected name.
     */
    @Test
    void buscarEmpleadoPorId_existente(){

        Empleado empleado = new Empleado(1, "Carlos", "Ventas", 28000.0);
        when(empleadoRepostory.findById(1)).thenReturn(Optional.of(empleado));

        Empleado resultado = empleadoServicio.buscarEmpleadoPorId(1);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Carlos");
    }

    /**
     * Tests {@link EmpleadoServicio#buscarEmpleadoPorId(int)} when the employee does not exist.
     *
     * Mocks the repository to return {@link Optional#empty()} for a non-existent ID.
     * Asserts that the service returns {@code null} as expected.
     */
    @Test
    void buscarEmpleadoPorId_noExistente(){

        when(empleadoRepostory.findById(99)).thenReturn(Optional.empty());

        Empleado resultado = empleadoServicio.buscarEmpleadoPorId(99);

        assertThat(resultado).isNull();
    }

    /**
     * Verifies that {@link EmpleadoServicio#guardarEmpleado(Empleado)} persists the employee
     * by calling {@link EmpleadoRepository#save(Object)} and returns the saved entity.
     *
     * The test uses a new employee (with null ID) and checks that the repository's save
     * method is invoked exactly once.
     */
    @Test
    void guardarEmpleado_debePersistir(){

        Empleado empleado = new Empleado(null, "Laura", "Finanzas", 4000.0);
        when(empleadoRepostory.save(empleado)).thenReturn(empleado);

        Empleado resultado = empleadoServicio.guardarEmpleado(empleado);

        assertThat(resultado).isNotNull();
        verify(empleadoRepostory).save(empleado);
    }

    /**
     * Tests that {@link EmpleadoServicio#eliminarEmpleado(Empleado)} correctly delegates
     * the deletion to {@link EmpleadoRepository#delete(Object)}.
     *
     * Verifies that the repository's delete method is called exactly once with the
     * provided employee object. No return value is checked since the method is void.
     */
    @Test
    void eliminarEmpleado_debeInvocarDelete(){

        Empleado empleado = new Empleado(1, "Peter", "IT", 3200.0);

        empleadoServicio.eliminarEmpleado(empleado);

        verify(empleadoRepostory).delete(empleado);
    }
}
