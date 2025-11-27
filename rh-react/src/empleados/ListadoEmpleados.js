import axios from 'axios';
import React, { useEffect, useState } from 'react'

export default function ListadoEmpleados() {

    const urlBase = "http://localhost:8080/rh-app/empleados";
    
    const [empleados, setEmpleados] = useState([]);

    useEffect(() => {
        cargarEmpleados();
    }, []);

    const cargarEmpleados = async () => {
        const resultado = await axios.get(urlBase);
        console.log("Respuesta API:");
        console.log(resultado.data);
        setEmpleados(resultado.data);
    }

  return (
    <div className ='container'>
        <div className='container text-center' style={{margin: '30px'}}>
            <h3> Sistema de Recursos Humanos</h3>
        </div>
        <table className="table table-striped table-hover align-middle">
            <thead className='table-dark'>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Empleado</th>
                    <th scope="col">Departamento</th>
                    <th scope="col">Sueldo</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
            </tbody>
        </table>
    </div>
  )
}
       