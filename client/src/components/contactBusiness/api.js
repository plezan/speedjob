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

export function addContactWithBusiness(
  firstName,
  lastName,
  mail,
  phone,
  job,
  password,
  businessName,
  siret,
  businessWebsiteUrl
) {
  let body = new FormData();
  body.set('firstName', firstName);
  body.set('lastName', lastName);
  body.set('mail', mail);
  body.set('phone', phone);
  body.set('job', job);
  body.set('password', password);
  body.set('businessName', businessName);
  body.set('siret', siret);
  body.set('businessWebsiteUrl', businessWebsiteUrl);

  return axios.post('/back/contactsBusiness/addOneWithBusiness', body)
    .then((response) => {

    })
    .catch((error) => {
      console.error(error);
    });
}
