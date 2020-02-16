import 'react-app-polyfill/ie11';
import 'react-app-polyfill/stable';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './app/App';
import * as serviceWorker from './serviceWorker';
import axios from 'axios';

axios.defaults.xsrfHeaderName = 'X-XSRF-SPRING-REACT-APP-TOKEN';
axios.defaults.xsrfCookieName = 'XSRF-SPRING-REACT-APP-TOKEN';

ReactDOM.render(< App/>, document.getElementById('spring-react-app'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
