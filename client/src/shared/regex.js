export function isPhoneValid(phone) {
  return /^(0|\+33)[1-9]([-. ]?[0-9]{2}){4}$/.test(phone);
}

export function isMailValid(mail) {
  return /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(mail);
}

export function isSiretValid(siret) {
  return /^((RCS )?([0-9]{3} ){2}[0-9]{3}$|^([0-9]{3} ){3}[0-9]{5})|\s*$/.test(siret);
}

export function isWebsiteUrlValid(websiteUrl) {
  return /^((http:\/\/|https:\/\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?)|\s*$/.test(websiteUrl);
}

export function isFrenchPostalCodeValid(postalCode) {
  return /^([0-9]){5}$/.test(postalCode);
}
