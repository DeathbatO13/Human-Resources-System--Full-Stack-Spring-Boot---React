import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

/**
 * Component responsible for creating a new employee.
 * Provides a form fields to capture name, department, and salary,
 * then sends the data to the backend via POST request.
 *
 * @component
 * @returns {JSX.Element} Form to add a new employee
 */
export default function AgregarEmpleado() {

    const navigate = useNavigate();

    /** Base URL of the employees REST API */
    const urlBase = "http://localhost:8080/rh-app/empleados";

    /** State holding the new employee data */
    const [empleado, setEmpleado] = useState({
        nombre: '',
        departamento: '',
        sueldo: ''
    });

    const { nombre, departamento, sueldo } = empleado;

    /**
     * Handles changes in form inputs and updates the employee state.
     *
     * @param {React.ChangeEvent<HTMLInputElement>} e - Input change event
     */
    const onInputChange = (e) => {
        setEmpleado({ ...empleado, [e.target.name]: e.target.value });
    };

    /**
     * Handles form submission: sends the new employee to the backend
     * and redirects to the employee list upon success.
     *
     * @param {React.FormEvent<HTMLFormElement>} e - Form submit event
     */
    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post(urlBase, empleado);
            navigate('/');
        } catch (error) {
            console.error("Error al agregar el empleado:", error);
            alert("Ocurrió un error al guardar el empleado. Verifique los datos.");
        }
    };

    return (
    <div className='container'>
        <div className='container text-center' style={{margin: '30px'}}>
            <h3>Agregar Empleado</h3>
        </div>
        <form onSubmit={(e)=>onSubmit(e)}>
            <div className="mb-3">
                <label htmlFor="nombre" className="form-label">Nombre</label>
                <input type="text" className="form-control" id="nombre" name="nombre" required={true}
                value={nombre} onChange={(e)=>onInputChange(e)}/>
    
            </div>

            <div className="mb-3">
                <label htmlFor="departamento" className="form-label">Departamento</label>
                <input type="text" className="form-control" id="departamento" name="departamento"
                value={departamento} onChange={(e)=>onInputChange(e)}/>
            </div>

            <div className="mb-3">
                <label htmlFor="sueldo" className="form-label">Sueldo</label>
                <input type="number" step="any" className="form-control" id="sueldo" name="sueldo"
                value={sueldo} onChange={(e)=>onInputChange(e)}/>
            </div>

            <div className='text-center'>
                <button type="submit" className="btn btn-warning btn-sm me-3">Agregar</button>
                <a href="/" className="btn btn-danger btn-sm">Regresar</a>
            </div>
        </form>
            
    </div>
    )
}
