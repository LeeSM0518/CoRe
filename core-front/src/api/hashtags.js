import { hashtags } from './common/index.js';

function getListByName(name) {
  return hashtags.get('', {
    params: {
      name: name,
    },
  });
}

export { getListByName };
