import { accounts } from './common/index.js';

function requestCodeToEmail(email) {
  return accounts.post('password/email', email);
}

function requestToResetPassword(verification) {
  return accounts.post('password/reset', verification);
}

export { requestCodeToEmail, requestToResetPassword };
