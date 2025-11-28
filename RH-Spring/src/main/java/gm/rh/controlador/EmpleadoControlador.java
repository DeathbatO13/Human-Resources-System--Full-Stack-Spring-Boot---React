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

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")

public class EmpleadoControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        var empleados= empleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleadoa a agregar: " + empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }


    @GetMapping("empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoporId(@PathVariable Integer id){

        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);

        if(empleado == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro id: " +id);

        return ResponseEntity.ok(empleado);
    }

    @PutMapping("empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpelado(@PathVariable Integer id, @RequestBody Empleado recibido) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoExcepcion("El id no existe: "+ id);
        }

        empleado.setNombre(recibido.getNombre());
        empleado.setDepartamento(recibido.getDepartamento());
        empleado.setSueldo(recibido.getSueldo());

        empleadoServicio.guardarEmpleado(empleado);

        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){

        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null){
            throw new RecursoNoEncontradoExcepcion("Id recibido no existe: " + id);
        }

        empleadoServicio.eliminarEmpleado(empleado);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
