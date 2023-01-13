import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import HeaderComponent from './Layout/HeaderComponent';
import Home from './Pages/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CreateEmployeeComponent from './Components/CreateEmployeeComponent';
import UpdateEmployeeComponent from './Components/UpdateEmployeeComponent';
import ViewEmployeeComponent from './Components/ViewEmployeeComponent';

function App() {
  return (
    <div className="App">
      <Router>
      <HeaderComponent />
      <Routes>
        <Route exact path="/" element={<Home/>} />
        <Route exact path="/add-employee" element={<CreateEmployeeComponent />} />
        <Route exact path="/update-employee/:id" element={<UpdateEmployeeComponent />} />
        <Route exact path="/view-employee/:id" element={<ViewEmployeeComponent />} />
      </Routes>
      </Router>
      
    </div>
  );
}

export default App;
