package gm.rh.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import gm.rh.modelo.Empleado;
import gm.rh.service.IEmpleadoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmpleadoControlador.class)
public class EmpleadoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IEmpleadoServicio empleadoServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerEmpleados_debeRetornarLista() throws Exception{
        when(empleadoServicio.listarEmpleados()).thenReturn(List.of(
                new Empleado(1, "Juan", "IT", 3000.0)));

        mockMvc.perform(get("/rh-app/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));

    }

    @Test
    void obtenerEmpleadoPorId_existente() throws Exception{
        when(empleadoServicio.buscarEmpleadoPorId(1))
                .thenReturn(new Empleado(1, "Ana", "HR", 2500.0));

        mockMvc.perform(get("/rh-app/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"));

    }

    @Test
    void obtenerEmpleadoPorId_noExistente() throws Exception{

        when(empleadoServicio.buscarEmpleadoPorId(99)).thenReturn(null);

        mockMvc.perform(get("/rh-app/empleados/99"))
                .andExpect(status().isNotFound());

    }

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

    @Test
    void eliminarEmpleado_existente() throws Exception {
        Empleado empleado = new Empleado(1, "Mario", "IT", 3200.0);
        when(empleadoServicio.buscarEmpleadoPorId(1)).thenReturn(empleado);

        mockMvc.perform(delete("/rh-app/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eliminado").value(true));
    }


}
