function saveMemberToCookie(name) {
  document.cookie = `core_member_name=${name}`;
}

function getMemberFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)core_member_name\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function deleteCookie(value) {
  document.cookie = `${value}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

export { saveMemberToCookie, getMemberFromCookie, deleteCookie };
