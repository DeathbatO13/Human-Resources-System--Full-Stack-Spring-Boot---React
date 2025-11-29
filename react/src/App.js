import { BrowserRouter, Route, Routes } from "react-router-dom";
import ListadoEmpleados from "./empleados/ListadoEmpleados";
import AgregarEmpleado from './empleados/AgregarEmpleado';
import EditarEmpleado from './empleados/EditarEmpleado';
import Navegacion from "./plantilla/Navegacion";


function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <Navegacion />
        <Routes>
          <Route exact path="/" element={<ListadoEmpleados />}/>
          <Route exact path="/agregar" element={<AgregarEmpleado />}/>
          <Route exact path="/editar/:id" element={<EditarEmpleado />}/>
        </Routes>

      </BrowserRouter>
    </div>
  );
}

export default App;
