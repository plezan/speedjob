export function isPhoneValid(phone) {
  return /^(?:(?:\+|00)33[\s.-]{0,3}(?:\(0\)[\s.-]{0,3})?|0)[1-9](?:(?:[\s.-]?\d{2}){4}|\d{2}(?:[\s.-]?\d{3}){2})$/.test(phone);
}

export function isMailValid(mail) {
  return /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(mail);
}

export function isSiretValid(siret) {
  return /^(RCS ?)?([0-9]{3} ?){2}[0-9]{3}$|^([0-9]{3} ?){3}[0-9]{5}$/.test(siret);
}

export function isWebsiteUrlValid(websiteUrl) {
  return /^((http(s)?:\/\/)[-a-zA-Z0-9:@;?&=\/%\+\.\*!'\(\),\$_\{\}\^~\[\]`#|]+)$/.test(websiteUrl);
}

export function isFrenchPostalCodeValid(postalCode) {
  return /^([0-9]){5}$/.test(postalCode);
}
