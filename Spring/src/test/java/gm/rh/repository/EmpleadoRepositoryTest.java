package gm.rh.repository;

import gm.rh.modelo.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link EmpleadoRepository}.
 *
 * Uses {@code @DataJpaTest} to load only the JPA layer, automatically configuring
 * an in-memory database (H2 by default) and enabling repository beans.
 */
@DataJpaTest
public class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepostory empleadoRepostory;

    /**
     * Tests the basic save and find-by-ID functionality of the repository.
     *
     * Creates a new {@link Empleado} entity (with null ID), persists it using
     * {@link EmpleadoRepository#save(Object)}, and verifies that:
     * <ul>
     *   <li>The saved entity has a generated ID (not null).</li>
     *   <li>The entity can be successfully retrieved by its ID using
     *       {@link EmpleadoRepository#findById(Object)}.</li>
     * </ul>
     *
     * This ensures that the repository is correctly wired and that basic CRUD
     * operations work as expected with the auto-configured test database.
     *
     * @throws Exception if any unexpected error occurs during persistence or retrieval
     */
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
