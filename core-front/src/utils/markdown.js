import hljs from 'highlight.js';
var MarkdownIt = require('markdown-it');
var languageOverrides = {
  js: 'javascript',
  html: 'xml',
};
export const md = new MarkdownIt({
  html: true,
  highlight: function(code, lang) {
    if (languageOverrides[lang]) lang = languageOverrides[lang];
    if (lang && hljs.getLanguage(lang)) {
      try {
        return hljs.highlight(lang, code).value;
      } catch (e) {}
    }
    return '';
  },
}).use(require('markdown-it-footnote'));
