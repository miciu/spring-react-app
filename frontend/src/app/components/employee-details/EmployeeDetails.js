import React from 'react';

class EmployeeDetails extends React.Component {
  render() {
    return (
      <tr>
        <td>{this.props.employee.firstName}</td>
        <td>{this.props.employee.lastName}</td>
        <td>{this.props.employee.age}</td>
      </tr>
    )
  }
}

export default EmployeeDetails;