import React from 'react';

class AddEmployeeModal extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      age: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    let state = {};
    state[event.target.name] = event.target.value;
    this.setState(state);
  }

  handleSubmit(event) {
    event.preventDefault();
    let newEmployee = {
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      age: this.state.age
    };
    this.props.onCreate(newEmployee);
    this.setState({
        firstName: '',
        lastName: '',
        age: ''
      }
    );
  }

  render() {
    return (
      <div>
        <div>
          <form onSubmit={this.handleSubmit}>
            <h2>Add new employee</h2>
            <div>
              <div>
                <label htmlFor="firstName">First Name: </label>
              </div>
              <div>
                <input type="text" name="firstName" value={this.state.firstName} onChange={this.handleChange}/>
              </div>
            </div>
            <div>
              <div>
                <label htmlFor="lastName">Last Name: </label>
              </div>
              <div>
                <input type="text" name="lastName" value={this.state.lastName} onChange={this.handleChange}/>
              </div>
            </div>
            <div>
              <div>
                <label htmlFor="age">Age: </label>
              </div>
              <div>
                <input type="text" name="age" value={this.state.age} onChange={this.handleChange}/>
              </div>
            </div>
            <div>
              <button type="submit">Add</button>
            </div>
          </form>
        </div>
      </div>
    )
  }
}

export default AddEmployeeModal;