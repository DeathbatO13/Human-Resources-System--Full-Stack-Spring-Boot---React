package gm.rh.service;

import gm.rh.modelo.Empleado;
import gm.rh.repository.EmpleadoRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation responsible for managing employee-related business logic.
 * Provides CRUD operations for the {@link Empleado} entity by delegating persistence
 * tasks to the {@link EmpleadoRepostory}.
 */
@Service
public class EmpleadoServicio implements IEmpleadoServicio {

    @Autowired
    private EmpleadoRepostory empleadoRepostory;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepostory.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        return empleadoRepostory.findById(idEmpleado).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepostory.save(empleado);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepostory.delete(empleado);
    }
}