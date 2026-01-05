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

@ExtendWith(MockitoExtension.class)
public class EmpleadoServicioTest {

    @Mock
    private EmpleadoRepostory empleadoRepostory;

    @InjectMocks
    private EmpleadoServicio empleadoServicio;

    @Test
    void listarEmpleados_debeRetornarLista(){

        Empleado e1 = new Empleado(1, "Juan", "IT", 3000.0);
        Empleado e2 = new Empleado(2,"Ana", "HR", 2500.0);

        when(empleadoRepostory.findAll()).thenReturn(List.of(e1, e2));

        List<Empleado> resultado = empleadoServicio.listarEmpleados();

        assertThat(resultado).hasSize(2);
        verify(empleadoRepostory).findAll();

    }

    @Test
    void buscarEmpleadoPorId_existente(){

        Empleado empleado = new Empleado(1, "Carlos", "Ventas", 28000.0);
        when(empleadoRepostory.findById(1)).thenReturn(Optional.of(empleado));

        Empleado resultado = empleadoServicio.buscarEmpleadoPorId(1);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Carlos");
    }

    @Test
    void buscarEmpleadoPorId_noExistente(){

        when(empleadoRepostory.findById(99)).thenReturn(Optional.empty());

        Empleado resultado = empleadoServicio.buscarEmpleadoPorId(99);

        assertThat(resultado).isNull();
    }

    @Test
    void guardarEmpleado_debePersistir(){

        Empleado empleado = new Empleado(null, "Laura", "Finanzas", 4000.0);
        when(empleadoRepostory.save(empleado)).thenReturn(empleado);

        Empleado resultado = empleadoServicio.guardarEmpleado(empleado);

        assertThat(resultado).isNotNull();
        verify(empleadoRepostory).save(empleado);
    }

    @Test
    void eliminarEmpleado_debeInvocarDelete(){

        Empleado empleado = new Empleado(1, "Peter", "IT", 3200.0);

        empleadoServicio.eliminarEmpleado(empleado);

        verify(empleadoRepostory).delete(empleado);
    }
}
