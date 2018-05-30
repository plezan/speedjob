import axios from 'axios';

/**
 *
 */
export function getAllBusinesses() {
  return axios.get('/back/businesses')
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });
}
