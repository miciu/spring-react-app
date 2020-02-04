import axios from 'axios';

const ENDPOINT_URL = '/employee';

const EmployeeService = {
  fetchEmployees() {
    return axios.get(ENDPOINT_URL).then(
      (result) => {
        return result.data;
      }
    );
  },

  addEmployee(newEmployee) {
    return axios.post(ENDPOINT_URL, newEmployee).then(
      (result) => {
        console.log('Status: ' + result.status);
        if (result.status === 200) {
          return newEmployee;
        }
        return undefined;
      });
  }
};

export default EmployeeService;
