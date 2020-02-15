import React from 'react';
import EmployeeDetails from '../employee-details/EmployeeDetails';

class EmployeeList extends React.Component {
  render() {
    const employees = this.props.employees.map(employee =>
      <EmployeeDetails employee={employee}/>
    );
    return (
      <table>
        <tbody>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Age</th>
          <th>Sector</th>
        </tr>
        {employees}
        </tbody>
      </table>
    )
  }
}

export default EmployeeList;