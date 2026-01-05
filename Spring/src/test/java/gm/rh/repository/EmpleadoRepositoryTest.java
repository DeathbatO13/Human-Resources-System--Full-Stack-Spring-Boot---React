package gm.rh.repository;

import gm.rh.modelo.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepostory empleadoRepostory;

    @Test
    void guardarYBuscarEmpleado() {
        Empleado empleado = new Empleado(null, "Sofia", "Finanzas", 4500.0);

        Empleado guardado = empleadoRepostory.save(empleado);

        assertThat(guardado.getIdEmpleado()).isNotNull();
        assertThat(
                empleadoRepostory.findById(guardado.getIdEmpleado())
        ).isPresent();
    }
}
