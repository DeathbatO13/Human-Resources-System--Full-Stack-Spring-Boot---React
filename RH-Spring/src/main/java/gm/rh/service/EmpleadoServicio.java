package gm.rh.service;

import gm.rh.modelo.Empleado;
import gm.rh.repository.EmpleadoRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
EmpleadoServicio implements IEmpleadoServicio{

    @Autowired
    private EmpleadoRepostory empleadoRepostory;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepostory.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        return empleadoRepostory.findById(idEmpleado).orElse(null);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepostory.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepostory.delete(empleado);
    }
}
