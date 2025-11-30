import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';

/**
 * Component for editing an existing employee or creating a new one when no ID is provided.
 * If an `id` is present in the URL, it loads the employee data and allows editing.
 * Otherwise, it works as an "Add Employee" form (you can reuse this component for both).
 *
 * @component
 * @returns {JSX.Element} Form to edit or create an employee
 */
export default function EditarEmpleado() {

    const navigate = useNavigate();
    const { id } = useParams();

    /** Base URL of the employees REST API */
    const urlBase = "http://localhost:8080/rh-app/empleados";

    /** Employee state – initialized with empty/default values */
    const [empleado, setEmpleado] = useState({
        nombre: '',
        departamento: '',
        sueldo: ''
    });

    const { nombre, departamento, sueldo } = empleado;

    /**
     * Loads employee data from the backend when editing (id is present).
     */
    useEffect(() => {
        if (id) {
            cargarEmpleado();
        }
    }, [id]);

    /**
     * Fetches a single employee by ID and populates the form.
     */
    const cargarEmpleado = async () => {
        try {
            const resultado = await axios.get(`${urlBase}/${id}`);
            setEmpleado(resultado.data);
        } catch (error) {
            console.error("Error al cargar el empleado:", error);
            alert("No se pudo cargar el empleado. Puede que no exista.");
        }
    };

    /**
     * Generic handler for input changes.
     *
     * @param {React.ChangeEvent<HTMLInputElement>} e - Input change event
     */
    const onInputChange = (e) => {
        setEmpleado({ ...empleado, [e.target.name]: e.target.value });
    };

    /**
     * Handles form submission – creates or updates the employee.
     *
     * @param {React.FormEvent<HTMLFormElement>} e - Form submit event
     */
    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            if (id) {
                // Update existing employee
                await axios.put(`${urlBase}/${id}`, empleado);
            } else {
                // Create new employee
                await axios.post(urlBase, empleado);
            }
            navigate('/');
        } catch (error) {
            console.error("Error al guardar el empleado:", error);
            alert("Ocurrió un error al guardar el empleado.");
        }
    };

    return (
        <div className='container'>
                <div className='app-hero'>
                        <div>
                                <div className='app-title'>Editar Empleado</div>
                                <div className='app-sub'>Actualiza los datos del empleado</div>
                        </div>
                </div>

                <div className='table-card'>
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

            <div style={{display:'flex', justifyContent:'center', gap:10}}>
                <button type="submit" className="btn-modern primary">Guardar</button>
                <a href="/" className="btn-modern ghost">Regresar</a>
            </div>
                    </form>
                </div>
    </div>
  )
  
}
