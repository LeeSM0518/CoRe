import { accounts } from './common/index.js';

function requestCodeToEmail(email) {
  return accounts.post('signup/email', email);
}

function requestVerificateEmail(verification) {
  return accounts.post('signup/code', verification);
}

function requestToSignUp(member) {
  return accounts.post('signup', member);
}

export { requestCodeToEmail, requestVerificateEmail, requestToSignUp };
