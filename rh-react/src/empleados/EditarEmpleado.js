import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';

export default function EditarEmpleado() {

    let navegacion = useNavigate();
    const urlBase = "http://localhost:8080/rh-app/empleados";

    const {id} = useParams();

    const [empleado, setEmpleado] = useState({
        nombre: '',
        departamento: '',
        sueldo: 0
    });

    const { nombre, departamento, sueldo } = empleado;


    const cargarEmpleado = async () => {
        const resultado = await axios.get(`${urlBase}/${id}`);
        setEmpleado(resultado.data);
    }

     useEffect(() => {
        cargarEmpleado();
    }, []);

    const onInputChange = (e) => {
        setEmpleado({ ...empleado, [e.target.name]: e.target.value });
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`${urlBase}/${id}`, empleado);
        navegacion('/');
    }
  

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
