package gm.rh.service;

import gm.rh.modelo.Empleado;

import java.util.List;
/**
 * Service interface for managing employee operations.
 * Defines the contract for CRUD operations on the {@link Empleado} entity.
 */
public interface IEmpleadoServicio {

    /**
     * Retrieves all employees registered in the system.
     *
     * @return a {@link List} containing all {@link Empleado} entities.
     *         Returns an empty list if no employees exist, never {@code null}.
     */
    List<Empleado> listarEmpleados();

    /**
     * Finds an employee by their unique identifier.
     *
     * @param idEmpleado the unique ID of the employee to retrieve
     * @return the {@link Empleado} with the specified ID, or {@code null} if not found
     */
    Empleado buscarEmpleadoPorId(Integer idEmpleado);

    /**
     * Saves an employee to the database.
     * If the employee has no ID (or ID is null), a new record is created.
     * If the employee already exists, the record is updated.
     *
     * @param empleado the {@link Empleado} entity to save or update
     * @return the saved {@link Empleado} entity, with its ID populated if it was a new record
     */
    Empleado guardarEmpleado(Empleado empleado);

    /**
     * Deletes the specified employee from the database.
     *
     * @param empleado the {@link Empleado} entity to be removed
     */
    void eliminarEmpleado(Empleado empleado);
}