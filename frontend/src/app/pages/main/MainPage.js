import React from 'react';
import EmployeeList from '../../components/employee-list/EmployeeList';
import EmployeeService from '../../services/EmployeeService';
import AddEmployeeModal from '../../components/add-employee-modal/AddEmployeeModal';

class MainPage extends React.Component {

  constructor(props) {
    super(props);
    this.state = { employees: [] };
    this.addEmployee = this.addEmployee.bind(this);
  }

  componentDidMount() {
    EmployeeService.fetchEmployees()
      .then(employees => {
        this.setState({ employees: employees });
      }).catch(error => {
      console.error('Error occur: ' + error)
    });
  }

  addEmployee(newEmployee) {
    let employees = this.state.employees;
    EmployeeService.addEmployee(newEmployee).then(addedEmployee => {
      if (addedEmployee != undefined) {
        employees.push(addedEmployee);
        this.setState({ employees: employees });
      } else {
        console.error('Error: could not add new employee');
      }
    }).catch(error => {
      console.error('Error occur: ' + error)
    });
  }

  render() {
    return (
      <div>
        <div>
          <EmployeeList employees={this.state.employees}/>
        </div>
        <div>
          <AddEmployeeModal onCreate={this.addEmployee}/>
        </div>
      </div>
    )
  }
}

export default MainPage;
