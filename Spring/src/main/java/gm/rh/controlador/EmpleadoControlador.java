package gm.rh.controlador;

import gm.rh.excepcion.RecursoNoEncontradoExcepcion;
import gm.rh.modelo.Empleado;
import gm.rh.service.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller that handles HTTP requests related to employees.
 * Provides endpoints for CRUD operations on the {@link Empleado} resource.
 *
 * All endpoints are prefixed with "/rh-app".
 */
@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    /**
     * Retrieves the complete list of employees.
     *
     * @return List of all {@link Empleado} entities
     */
    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados() {
        var empleados = empleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    /**
     * Creates a new employee.
     *
     * @param empleado the employee data received in the request body
     * @return the created {@link Empleado} entity with its assigned ID
     */
    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        logger.info("Empleado a agregar: " + empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }

    /**
     /**
     * Retrieves an employee by its ID.
     *
     * @param id the employee ID
     * @return ResponseEntity containing the {@link Empleado} if found
     * @throws RecursoNoEncontradoExcepcion if no employee exists with the given ID
     */
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoporId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro id: " + id);
        }
        return ResponseEntity.ok(empleado);
    }

    /**
     * Updates an existing employee.
     *
     * @param id        the ID of the employee to update
     * @param recibido  the employee data received in the request body
     * @return ResponseEntity containing the updated {@link Empleado}
     * @throws RecursoNoEncontradoExcepcion if the employee with the given ID does not exist
     */
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpelado(@PathVariable Integer id,
                                                       @RequestBody Empleado recibido) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("El id no existe: " + id);
        }

        empleado.setNombre(recibido.getNombre());
        empleado.setDepartamento(recibido.getDepartamento());
        empleado.setSueldo(recibido.getSueldo());

        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    /**
     * Deletes an employee by its ID.
     *
     * @param id the ID of the employee to delete
     * @return ResponseEntity with a map indicating the deletion result
     * @throws RecursoNoEncontradoExcepcion if the employee with the given ID does not exist
     */
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("Id recibido no existe: " + id);
        }

        empleadoServicio.eliminarEmpleado(empleado);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
