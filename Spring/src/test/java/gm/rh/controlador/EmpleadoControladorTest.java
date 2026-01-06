package gm.rh.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import gm.rh.modelo.Empleado;
import gm.rh.service.IEmpleadoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test class for {@link EmpleadoControlador}.
 * Uses {@code @WebMvcTest} to load only the web layer (controller) and mock the service.
 */
@WebMvcTest(EmpleadoControlador.class)
class EmpleadoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEmpleadoServicio empleadoServicio;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Verifies that the GET /rh-app/empleados endpoint returns a list of employees
     * with HTTP status 200 (OK) when the service returns at least one employee.
     *
     * Mocks {@link IEmpleadoServicio#listarEmpleados()} to return a list containing
     * a single employee and checks that the JSON response contains the expected name
     * in the first position.
     *
     * @throws Exception if an error occurs during the simulated HTTP request
     */
    @Test
    void obtenerEmpleados_debeRetornarLista() throws Exception {
        when(empleadoServicio.listarEmpleados()).thenReturn(
                List.of(new Empleado(1, "Juan", "IT", 3000.0))
        );

        mockMvc.perform(get("/rh-app/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    /**
     * Tests the GET /rh-app/empleados/{id} endpoint when the requested employee exists.
     *
     * Configures the service mock so that {@link IEmpleadoServicio#buscarEmpleadoPorId(int)}
     * returns an employee with ID 1. Verifies that the response has status 200 (OK)
     * and that the JSON body contains the expected employee data.
     *
     * @throws Exception if an error occurs during the simulated HTTP request
     */
    @Test
    void obtenerEmpleadoPorId_existente() throws Exception {
        when(empleadoServicio.buscarEmpleadoPorId(1))
                .thenReturn(new Empleado(1, "Ana", "HR", 2500.0));

        mockMvc.perform(get("/rh-app/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"));
    }

    /**
     * Tests the GET /rh-app/empleados/{id} endpoint when the requested employee does not exist.
     *
     * Mocks the service to return {@code null} when searching for a non-existent ID (99).
     * Expects the controller to respond with HTTP status 404 (Not Found).
     *
     * @throws Exception if an error occurs during the simulated HTTP request
     */
    @Test
    void obtenerEmpleadoPorId_noExiste() throws Exception {
        when(empleadoServicio.buscarEmpleadoPorId(99)).thenReturn(null);

        mockMvc.perform(get("/rh-app/empleados/99"))
                .andExpect(status().isNotFound());
    }

    /**
     * Verifies the POST /rh-app/empleados endpoint for creating a new employee.
     *
     * Sends an {@link Empleado} object in JSON format (without ID, as it is assigned by the DB).
     * Mocks the service to return the same object received. Checks that the response
     * has status 200 (OK) and that the returned employee contains the sent data.
     *
     * @throws Exception if an error occurs during the simulated HTTP request
     */
    @Test
    void agregarEmpleado() throws Exception {
        Empleado empleado = new Empleado(null, "Luis", "Ventas", 2700.0);
        when(empleadoServicio.guardarEmpleado(empleado)).thenReturn(empleado);

        mockMvc.perform(post("/rh-app/empleados")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Luis"));
    }

    /**
     * Tests the DELETE /rh-app/empleados/{id} endpoint when the employee to delete exists.
     *
     * Mocks the service to confirm the existence of the employee with ID 1.
     * Verifies that the response has status 200 (OK) and that the JSON body indicates
     * the employee was successfully deleted ({@code "eliminado": true}).
     *
     * @throws Exception if an error occurs during the simulated HTTP request
     */
    @Test
    void eliminarEmpleado_existente() throws Exception {
        Empleado empleado = new Empleado(1, "Mario", "IT", 3200.0);
        when(empleadoServicio.buscarEmpleadoPorId(1)).thenReturn(empleado);

        mockMvc.perform(delete("/rh-app/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eliminado").value(true));
    }
}