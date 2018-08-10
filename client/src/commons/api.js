import axios from 'axios';
import Router from '../router';

export function login(mail, password) {
  axios.post('/login', {mail, password})
    .then((data) => {
      Router.push('/');
    })
    .catch((error) => {
      console.error(error);
    });
}
