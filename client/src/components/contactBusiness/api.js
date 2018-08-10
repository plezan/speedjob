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
  activityArea,
  siret,
  businessWebsiteUrl
) {
  let body = new FormData();
  body.set('firstName', firstName);
  body.set('lastName', lastName);
  body.set('mail', mail);
  phone ? body.set('phone', phone) : null;
  body.set('job', job);
  body.set('password', password);
  body.set('businessName', businessName);
  body.set('activityArea', activityArea);
  siret ? body.set('siret', siret ) : null;
  businessWebsiteUrl ? body.set('businessWebsiteUrl', businessWebsiteUrl) : null;

  return axios.post('/back/contactsBusiness/withBusiness', body)
    .catch((error) => {
      console.error(error);
    });
}

export function addContactWithExistantBusiness(
  firstName,
  lastName,
  mail,
  phone,
  job,
  password,
  businessId
) {
  let body = new FormData();
  body.set('firstName', firstName);
  body.set('lastName', lastName);
  body.set('mail', mail);
  body.set('phone', phone);
  body.set('job', job);
  body.set('password', password);
  body.set('businessId', businessId);

  return axios.post('/back/contactsBusiness/withExistantBusiness', body)
    .catch((error) => {
      console.error(error);
    });
}
