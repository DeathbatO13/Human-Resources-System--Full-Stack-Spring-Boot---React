import axios from 'axios';
import { Link } from 'react-router-dom';
import React, { useEffect, useState } from 'react'
import { NumericFormat } from 'react-number-format';

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
                    <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                {
                    empleados.map( (empleado, indice) => ( 
                        <tr key={indice}>
                            <th scope="row">{empleado.idEmpleado}</th>
                            <td>{empleado.nombre}</td>
                            <td>{empleado.departamento}</td>
                            <td><NumericFormat
                                value={empleado.sueldo}
                                displayType={'text'}
                                thousandSeparator={true}
                                prefix={'$'}
                                decimalScale={2}
                                fixedDecimalScale={true}
                            /></td>
                            <td>
                                <Link to={`/editar/${empleado.idEmpleado}`} 
                                className="btn btn-primary btn-sm me-3">Editar</Link>
                                
                            </td>
                        </tr>
                    ))
                }
                  
            </tbody>
        </table>
    </div>
  )
}
       