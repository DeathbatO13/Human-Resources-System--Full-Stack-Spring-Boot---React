import axios from 'axios';
import { Link } from 'react-router-dom';
import React, { useEffect, useState } from 'react'
import { NumericFormat } from 'react-number-format';

/**
 * Component that displays the complete list of employees in a table.
 * Fetches employee data from the Spring Boot backend and provides options
 * to edit or delete each employee.
 *
 * @component
 * @returns {JSX.Element} Table with all registered employees
 */
export default function ListadoEmpleados() {

    /** Base URL of the employees REST API */
    const urlBase = "http://localhost:8080/rh-app/empleados";
    
    /** State that holds the array of employees loaded from the backend */
    const [empleados, setEmpleados] = useState([]);

    /**
     * Loads employees from the backend when the component mounts.
     */
    useEffect(() => {
        cargarEmpleados();
    }, []);

    /**
     * Fetches the full list of employees from the API and updates the state.
     */
    const cargarEmpleados = async () => {
        try {
            const resultado = await axios.get(urlBase);
            console.log("Respuesta API:", resultado.data);
            setEmpleados(resultado.data);
        } catch (error) {
            console.error("Error al cargar empleados:", error);
        }
    };

    /**
     * Deletes an employee by ID and refreshes the list.
     *
     * @param {number} id - The ID of the employee to delete
     */
    const eliminarEmpleado = async (id) => {
        try {
            await axios.delete(`${urlBase}/${id}`);
            cargarEmpleados(); // Refresh list after deletion
        } catch (error) {
            console.error("Error al eliminar empleado:", error);
        }
    };

    return (
        <div className="container">
            <div className="container text-center" style={{ margin: '30px' }}>
                <h3>Sistema de Recursos Humanos</h3>
            </div>

            <table className="table table-striped table-hover align-middle">
                <thead className="table-dark">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Empleado</th>
                        <th scope="col">Departamento</th>
                        <th scope="col">Sueldo</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {empleados.map((empleado, indice) => (
                        <tr key={indice}>
                            <th scope="row">{empleado.idEmpleado}</th>
                            <td>{empleado.nombre}</td>
                            <td>{empleado.departamento}</td>
                            <td>
                                <NumericFormat
                                    value={empleado.sueldo}
                                    displayType={'text'}
                                    thousandSeparator={true}
                                    prefix={'$'}
                                    decimalScale={2}
                                    fixedDecimalScale={true}
                                />
                            </td>
                            <td className="text-center">
                                <Link
                                    to={`/editar/${empleado.idEmpleado}`}
                                    className="btn btn-primary btn-sm me-3"
                                >
                                    Editar
                                </Link>
                                <button
                                    onClick={() => eliminarEmpleado(empleado.idEmpleado)}
                                    className="btn btn-danger btn-sm"
                                >
                                    Eliminar
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
       