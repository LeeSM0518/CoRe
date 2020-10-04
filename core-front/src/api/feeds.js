import { feeds } from './common/index.js';

function createFeed(feed) {
  return feeds.post('', feed);
}

function getFeedsForPage(page) {
  return feeds.get('', {
    params: {
      page: page,
    },
  });
}

export { createFeed, getFeedsForPage };
