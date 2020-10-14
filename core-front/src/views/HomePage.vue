<template>
  <v-container fluid>
    <v-row v-for="feed in feeds" :key="feed.id" align="start" justify="center">
      <v-col md="10" lg="8" sm="12">
        <v-card width="100%">
          <v-card-title>
            <v-avatar v-if="feed.writer.photo !== null" size="36">
              <img :src="feed.writer.photo" />
            </v-avatar>
            <v-avatar v-else size="32">
              <v-icon large color="grey">mdi-account-circle</v-icon>
            </v-avatar>
            <a
              class="black--text text-subtitle-1 ml-3"
              style="font-weight: 500;"
              >{{ feed.writer.name }}</a
            >

            <v-spacer></v-spacer>

            <v-menu bottom left>
              <template v-slot:activator="{ on, attrs }">
                <v-btn icon v-bind="attrs" v-on="on">
                  <v-icon>mdi-menu</v-icon>
                </v-btn>
              </template>

              <v-list>
                <v-list-item v-for="(item, i) in items" :key="i">
                  <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-card-title>
          <v-divider></v-divider>
          <v-row class="ma-1">
            <v-col cols="12">
              <p class="text-caption mb-1 ml-2 grey--text text--darken-1">
                {{ feed.relatedTags }}
              </p>
              <p class="text-h5 mb-n2 ml-2">{{ feed.title }}</p>
              <p class="text-caption mb-n3 ml-2 mt-3 grey--text">
                {{ feed.createdDateTime }}
              </p>
            </v-col>
            <v-col cols="12">
              <div :id="feed.id" class="markdown-body"></div>
            </v-col>
          </v-row>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="12" class="ml-1 mb-n3 mt-n3 ma-n1">
                <a class="grey--text text--darken-2 ml-2" @click="() => {}"
                  >자세히 보기</a
                >
              </v-col>

              <v-col cols="12" class="ml-1">
                <a
                  class="black--text"
                  @click="openLikeDialog(feed.membersWhoLike)"
                  >{{ feed.membersWhoLike.length }}</a
                >
                이 좋아합니다</v-col
              >
              <v-col cols="12" class="ml-1 mt-n5">
                <a class="black--text" @click="() => {}">{{
                  feed.countOfComments
                }}</a>
                의 댓글이 존재합니다.</v-col
              >
              <v-col cols="12">
                <v-btn icon>
                  <v-icon>mdi-thumb-up</v-icon>
                </v-btn>
                <v-btn icon>
                  <v-icon>mdi-comment</v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <v-btn @click="pushWrite" bottom fab fixed right rounded color="primary">
      <v-icon large>mdi-pencil-box</v-icon>
    </v-btn>
    <v-dialog v-model="likeDialog" width="500">
      <LikeDialog
        :members="membersWhoLike"
        @close-like-dialog="closeLikeDialog"
      ></LikeDialog>
    </v-dialog>
  </v-container>
</template>

<script>
import { md } from '@/utils/markdown.js';
import { getFeedsForPage } from '@/api/feeds.js';
import LikeDialog from '@/components/home/LikeDialog.vue';

export default {
  components: {
    LikeDialog,
  },

  data: () => ({
    feeds: [],
    likeDialog: false,
    membersWhoLike: [],
    items: [
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me 2' },
    ],
  }),

  methods: {
    openLikeDialog(members) {
      this.membersWhoLike = members;
      this.likeDialog = true;
    },

    closeLikeDialog() {
      this.likeDialog = false;
    },
    pushWrite() {
      this.$router.push('/write');
    },
    async getFeeds() {
      const result = await this.$store.dispatch('CALL_API', () =>
        getFeedsForPage(0),
      );
      if (result.status === 200) {
        result.data.forEach(element => {
          element.writer.photo =
            element.writer.photo !== null
              ? process.env.VUE_APP_STATIC_URL + element.writer.photo
              : null;
          element.relatedTags = element.relatedTags.map(v => {
            return '#'.concat(v);
          });
          element.relatedTags = element.relatedTags.join(' ');
          var date = new Date(element.createdDateTime);
          element.createdDateTime = `${date.getFullYear()}/${date.getMonth() +
            1}/${date.getDate()} ${date.getHours()}:${date.getMinutes()}`;
        });
        this.feeds = result.data;
      }
    },
  },

  mounted: async function() {
    await this.getFeeds();
    this.feeds.forEach(element => {
      document.getElementById(element.id).innerHTML = md.render(
        `\`\`\`${element.relatedTags.split('#')[1]}\n${
          element.mainCode
        }\n\`\`\``,
      );
    });
  },
};
</script>

<style>
/*

Original style from softwaremaniacs.org (c) Ivan Sagalaev <Maniac@SoftwareManiacs.Org>

*/

.hljs {
  display: block;
  overflow-x: auto;
  padding: 0.5em;
  background: #f0f0f0;
  -webkit-text-size-adjust: none;
}

.hljs,
.hljs-subst,
.hljs-tag .hljs-title,
.nginx .hljs-title {
  color: black;
}

.hljs-string,
.hljs-title,
.hljs-constant,
.hljs-parent,
.hljs-tag .hljs-value,
.hljs-rules .hljs-value,
.hljs-preprocessor,
.hljs-pragma,
.haml .hljs-symbol,
.ruby .hljs-symbol,
.ruby .hljs-symbol .hljs-string,
.hljs-template_tag,
.django .hljs-variable,
.smalltalk .hljs-class,
.hljs-addition,
.hljs-flow,
.hljs-stream,
.bash .hljs-variable,
.apache .hljs-tag,
.apache .hljs-cbracket,
.tex .hljs-command,
.tex .hljs-special,
.erlang_repl .hljs-function_or_atom,
.asciidoc .hljs-header,
.markdown .hljs-header,
.coffeescript .hljs-attribute {
  color: #800;
}

.smartquote,
.hljs-comment,
.hljs-annotation,
.diff .hljs-header,
.hljs-chunk,
.asciidoc .hljs-blockquote,
.markdown .hljs-blockquote {
  color: #888;
}

.hljs-number,
.hljs-date,
.hljs-regexp,
.hljs-literal,
.hljs-hexcolor,
.smalltalk .hljs-symbol,
.smalltalk .hljs-char,
.go .hljs-constant,
.hljs-change,
.lasso .hljs-variable,
.makefile .hljs-variable,
.asciidoc .hljs-bullet,
.markdown .hljs-bullet,
.asciidoc .hljs-link_url,
.markdown .hljs-link_url {
  color: #080;
}

.hljs-label,
.hljs-javadoc,
.ruby .hljs-string,
.hljs-decorator,
.hljs-filter .hljs-argument,
.hljs-localvars,
.hljs-array,
.hljs-attr_selector,
.hljs-important,
.hljs-pseudo,
.hljs-pi,
.haml .hljs-bullet,
.hljs-doctype,
.hljs-deletion,
.hljs-envvar,
.hljs-shebang,
.apache .hljs-sqbracket,
.nginx .hljs-built_in,
.tex .hljs-formula,
.erlang_repl .hljs-reserved,
.hljs-prompt,
.asciidoc .hljs-link_label,
.markdown .hljs-link_label,
.vhdl .hljs-attribute,
.clojure .hljs-attribute,
.asciidoc .hljs-attribute,
.lasso .hljs-attribute,
.coffeescript .hljs-property,
.hljs-phony {
  color: #88f;
}

.hljs-keyword,
.hljs-id,
.hljs-title,
.hljs-built_in,
.css .hljs-tag,
.hljs-javadoctag,
.hljs-phpdoc,
.hljs-dartdoc,
.hljs-yardoctag,
.smalltalk .hljs-class,
.hljs-winutils,
.bash .hljs-variable,
.apache .hljs-tag,
.hljs-type,
.hljs-typename,
.tex .hljs-command,
.asciidoc .hljs-strong,
.markdown .hljs-strong,
.hljs-request,
.hljs-status {
  font-weight: bold;
}

.asciidoc .hljs-emphasis,
.markdown .hljs-emphasis {
  font-style: italic;
}

.nginx .hljs-built_in {
  font-weight: normal;
}

.coffeescript .javascript,
.javascript .xml,
.lasso .markup,
.tex .hljs-formula,
.xml .javascript,
.xml .vbscript,
.xml .css,
.xml .hljs-cdata {
  opacity: 0.5;
}
@font-face {
  font-family: octicons-link;
  src: url(data:font/woff;charset=utf-8;base64,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)
    format('woff');
}

.markdown-body {
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  color: #333;
  font-family: 'Helvetica Neue', Helvetica, 'Segoe UI', Arial, freesans,
    sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
  font-size: 16px;
  line-height: 1.6;
  word-wrap: break-word;
}

.markdown-body a {
  background-color: transparent;
  -webkit-text-decoration-skip: objects;
}

.markdown-body a:active,
.markdown-body a:hover {
  outline-width: 0;
}

.markdown-body strong {
  font-weight: inherit;
}

.markdown-body strong {
  font-weight: bolder;
}

.markdown-body h1 {
  font-size: 2em;
  margin: 0.67em 0;
}

.markdown-body img {
  border-style: none;
}

.markdown-body svg:not(:root) {
  overflow: hidden;
}

.markdown-body code,
.markdown-body kbd,
.markdown-body pre {
  font-family: monospace, monospace;
  font-size: 1em;
}

.markdown-body hr {
  box-sizing: content-box;
  height: 0;
  overflow: visible;
}

.markdown-body input {
  font: inherit;
  margin: 0;
}

.markdown-body input {
  overflow: visible;
}

.markdown-body button:-moz-focusring,
.markdown-body [type='button']:-moz-focusring,
.markdown-body [type='reset']:-moz-focusring,
.markdown-body [type='submit']:-moz-focusring {
  outline: 1px dotted ButtonText;
}

.markdown-body [type='checkbox'] {
  box-sizing: border-box;
  padding: 0;
}

.markdown-body table {
  border-spacing: 0;
  border-collapse: collapse;
}

.markdown-body td,
.markdown-body th {
  padding: 0;
}

.markdown-body * {
  box-sizing: border-box;
}

.markdown-body input {
  font: 13px/1.4 Helvetica, arial, nimbussansl, liberationsans, freesans, clean,
    sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
}

.markdown-body a {
  color: #4078c0;
  text-decoration: none;
}

.markdown-body a:hover,
.markdown-body a:active {
  text-decoration: underline;
}

.markdown-body hr {
  height: 0;
  margin: 15px 0;
  overflow: hidden;
  background: transparent;
  border: 0;
  border-bottom: 1px solid #aaa;
}

.markdown-body hr::before {
  display: table;
  content: '';
}

.markdown-body hr::after {
  display: table;
  clear: both;
  content: '';
}

.markdown-body h1,
.markdown-body h2,
.markdown-body h3,
.markdown-body h4,
.markdown-body h5,
.markdown-body h6 {
  margin-top: 0;
  margin-bottom: 0;
  line-height: 1.5;
}

.markdown-body h1 {
  font-size: 30px;
}

.markdown-body h2 {
  font-size: 21px;
}

.markdown-body h3 {
  font-size: 16px;
}

.markdown-body h4 {
  font-size: 14px;
}

.markdown-body h5 {
  font-size: 12px;
}

.markdown-body h6 {
  font-size: 11px;
}

.markdown-body p {
  margin-top: 0;
  margin-bottom: 10px;
}

.markdown-body blockquote {
  margin: 0;
}

.markdown-body ul,
.markdown-body ol {
  padding-left: 0;
  margin-top: 0;
  margin-bottom: 0;
}

.markdown-body ol ol,
.markdown-body ul ol {
  list-style-type: lower-roman;
}

.markdown-body ul ul ol,
.markdown-body ul ol ol,
.markdown-body ol ul ol,
.markdown-body ol ol ol {
  list-style-type: lower-alpha;
}

.markdown-body dd {
  margin-left: 0;
}

.markdown-body code {
  font-family: Consolas, 'Liberation Mono', Menlo, Courier, monospace;
  font-size: 12px;
}

.markdown-body pre {
  margin-top: 0;
  margin-bottom: 0;
  font: 12px Consolas, 'Liberation Mono', Menlo, Courier, monospace;
}

.markdown-body .pl-0 {
  padding-left: 0 !important;
}

.markdown-body .pl-1 {
  padding-left: 3px !important;
}

.markdown-body .pl-2 {
  padding-left: 6px !important;
}

.markdown-body .pl-3 {
  padding-left: 12px !important;
}

.markdown-body .pl-4 {
  padding-left: 24px !important;
}

.markdown-body .pl-5 {
  padding-left: 36px !important;
}

.markdown-body .pl-6 {
  padding-left: 48px !important;
}

.markdown-body .form-select::-ms-expand {
  opacity: 0;
}

.markdown-body:before {
  display: table;
  content: '';
}

.markdown-body:after {
  display: table;
  clear: both;
  content: '';
}

.markdown-body > *:first-child {
  margin-top: 0 !important;
}

.markdown-body > *:last-child {
  margin-bottom: 0 !important;
}

.markdown-body a:not([href]) {
  color: inherit;
  text-decoration: none;
}

.markdown-body .anchor {
  display: inline-block;
  padding-right: 2px;
  margin-left: -18px;
}

.markdown-body .anchor:focus {
  outline: none;
}

.markdown-body h1,
.markdown-body h2,
.markdown-body h3,
.markdown-body h4,
.markdown-body h5,
.markdown-body h6 {
  margin-top: 1em;
  margin-bottom: 16px;
  font-weight: bold;
  line-height: 1.4;
}

.markdown-body h1 .octicon-link,
.markdown-body h2 .octicon-link,
.markdown-body h3 .octicon-link,
.markdown-body h4 .octicon-link,
.markdown-body h5 .octicon-link,
.markdown-body h6 .octicon-link {
  color: #000;
  vertical-align: middle;
  visibility: hidden;
}

.markdown-body h1:hover .anchor,
.markdown-body h2:hover .anchor,
.markdown-body h3:hover .anchor,
.markdown-body h4:hover .anchor,
.markdown-body h5:hover .anchor,
.markdown-body h6:hover .anchor {
  text-decoration: none;
}

.markdown-body h1:hover .anchor .octicon-link,
.markdown-body h2:hover .anchor .octicon-link,
.markdown-body h3:hover .anchor .octicon-link,
.markdown-body h4:hover .anchor .octicon-link,
.markdown-body h5:hover .anchor .octicon-link,
.markdown-body h6:hover .anchor .octicon-link {
  visibility: visible;
}

.markdown-body h1 {
  padding-bottom: 0.3em;
  font-size: 2.25em;
  line-height: 1.2;
  border-bottom: 1px solid #aaa;
}

.markdown-body h1 .anchor {
  line-height: 1;
}

.markdown-body h2 {
  padding-bottom: 0.3em;
  font-size: 1.75em;
  line-height: 1.225;
  border-bottom: 1px solid #aaa;
}

.markdown-body h2 .anchor {
  line-height: 1;
}

.markdown-body h3 {
  font-size: 1.5em;
  line-height: 1.43;
}

.markdown-body h3 .anchor {
  line-height: 1.2;
}

.markdown-body h4 {
  font-size: 1.25em;
}

.markdown-body h4 .anchor {
  line-height: 1.2;
}

.markdown-body h5 {
  font-size: 1em;
}

.markdown-body h5 .anchor {
  line-height: 1.1;
}

.markdown-body h6 {
  font-size: 1em;
  color: #777;
}

.markdown-body h6 .anchor {
  line-height: 1.1;
}

.markdown-body p,
.markdown-body blockquote,
.markdown-body ul,
.markdown-body ol,
.markdown-body dl,
.markdown-body table,
.markdown-body pre {
  margin-top: 0;
  margin-bottom: 16px;
}

.markdown-body hr {
  height: 4px;
  padding: 0;
  margin: 16px 0;
  background-color: #e7e7e7;
  border: 0 none;
}

.markdown-body ul,
.markdown-body ol {
  padding-left: 2em;
}

.markdown-body ul ul,
.markdown-body ul ol,
.markdown-body ol ol,
.markdown-body ol ul {
  margin-top: 0;
  margin-bottom: 0;
}

.markdown-body li > p {
  margin-top: 16px;
}

.markdown-body dl {
  padding: 0;
}

.markdown-body dl dt {
  padding: 0;
  margin-top: 16px;
  font-size: 1em;
  font-style: italic;
  font-weight: bold;
}

.markdown-body dl dd {
  padding: 0 16px;
  margin-bottom: 16px;
}

.markdown-body blockquote {
  padding: 0 15px;
  color: #777;
  border-left: 4px solid #ddd;
}

.markdown-body blockquote > :first-child {
  margin-top: 0;
}

.markdown-body blockquote > :last-child {
  margin-bottom: 0;
}

.markdown-body table {
  display: block;
  width: 100%;
  overflow: auto;
  word-break: normal;
  word-break: keep-all;
}

.markdown-body table th {
  font-weight: bold;
}

.markdown-body table th,
.markdown-body table td {
  padding: 6px 13px;
  border: 1px solid #ddd;
}

.markdown-body table tr {
  background-color: #fff;
  border-top: 1px solid #ccc;
}

.markdown-body table tr:nth-child(2n) {
  background-color: #f8f8f8;
}

.markdown-body img {
  max-width: 100%;
  box-sizing: content-box;
  background-color: #fff;
}

.markdown-body code {
  padding: 0;
  padding-top: 0.2em;
  padding-bottom: 0.2em;
  margin: 0;
  font-size: 85%;
  background-color: rgba(0, 0, 0, 0.04);
  border-radius: 3px;
}

.markdown-body code:before,
.markdown-body code:after {
  letter-spacing: -0.2em;
  content: '\00a0';
}

.markdown-body pre > code {
  padding: 0;
  margin: 0;
  font-size: 100%;
  word-break: normal;
  white-space: pre;
  background: transparent;
  border: 0;
}

.markdown-body .highlight {
  margin-bottom: 16px;
}

.markdown-body .highlight pre,
.markdown-body pre {
  padding: 16px;
  overflow: auto;
  font-size: 85%;
  line-height: 1.45;
  background-color: #e2e2e2;
  border-radius: 3px;
}

.markdown-body .highlight pre {
  margin-bottom: 0;
  word-break: normal;
}

.markdown-body pre {
  word-wrap: normal;
}

.markdown-body pre code {
  display: inline;
  max-width: initial;
  padding: 0;
  margin: 0;
  overflow: initial;
  line-height: inherit;
  word-wrap: normal;
  background-color: transparent;
  border: 0;
}

.markdown-body pre code:before,
.markdown-body pre code:after {
  content: normal;
}

.markdown-body kbd {
  display: inline-block;
  padding: 3px 5px;
  font-size: 11px;
  line-height: 10px;
  color: #555;
  vertical-align: middle;
  background-color: #fcfcfc;
  border: solid 1px #ccc;
  border-bottom-color: #bbb;
  border-radius: 3px;
  box-shadow: inset 0 -1px 0 #bbb;
}

.markdown-body .pl-c {
  color: #969896;
}

.markdown-body .pl-c1,
.markdown-body .pl-s .pl-v {
  color: #0086b3;
}

.markdown-body .pl-e,
.markdown-body .pl-en {
  color: #795da3;
}

.markdown-body .pl-s .pl-s1,
.markdown-body .pl-smi {
  color: #333;
}

.markdown-body .pl-ent {
  color: #63a35c;
}

.markdown-body .pl-k {
  color: #a71d5d;
}

.markdown-body .pl-pds,
.markdown-body .pl-s,
.markdown-body .pl-s .pl-pse .pl-s1,
.markdown-body .pl-sr,
.markdown-body .pl-sr .pl-cce,
.markdown-body .pl-sr .pl-sra,
.markdown-body .pl-sr .pl-sre {
  color: #183691;
}

.markdown-body .pl-v {
  color: #ed6a43;
}

.markdown-body .pl-id {
  color: #b52a1d;
}

.markdown-body .pl-ii {
  background-color: #b52a1d;
  color: #f8f8f8;
}

.markdown-body .pl-sr .pl-cce {
  color: #63a35c;
  font-weight: bold;
}

.markdown-body .pl-ml {
  color: #693a17;
}

.markdown-body .pl-mh,
.markdown-body .pl-mh .pl-en,
.markdown-body .pl-ms {
  color: #1d3e81;
  font-weight: bold;
}

.markdown-body .pl-mq {
  color: #008080;
}

.markdown-body .pl-mi {
  color: #333;
  font-style: italic;
}

.markdown-body .pl-mb {
  color: #333;
  font-weight: bold;
}

.markdown-body .pl-md {
  background-color: #ffecec;
  color: #bd2c00;
}

.markdown-body .pl-mi1 {
  background-color: #eaffea;
  color: #55a532;
}

.markdown-body .pl-mdr {
  color: #795da3;
  font-weight: bold;
}

.markdown-body .pl-mo {
  color: #1d3e81;
}

.markdown-body kbd {
  display: inline-block;
  padding: 3px 5px;
  font: 11px Consolas, 'Liberation Mono', Menlo, Courier, monospace;
  line-height: 10px;
  color: #555;
  vertical-align: middle;
  background-color: #fcfcfc;
  border: solid 1px #ccc;
  border-bottom-color: #bbb;
  border-radius: 3px;
  box-shadow: inset 0 -1px 0 #bbb;
}

.markdown-body .full-commit .btn-outline:not(:disabled):hover {
  color: #4078c0;
  border: 1px solid #4078c0;
}

.markdown-body :checked + .radio-label {
  position: relative;
  z-index: 1;
  border-color: #4078c0;
}

.markdown-body .octicon {
  display: inline-block;
  vertical-align: text-top;
  fill: currentColor;
}

.markdown-body .task-list-item {
  list-style-type: none;
}

.markdown-body .task-list-item + .task-list-item {
  margin-top: 3px;
}

.markdown-body .task-list-item input {
  margin: 0 0.2em 0.25em -1.6em;
  vertical-align: middle;
}

.markdown-body hr {
  border-bottom-color: #aaa;
}

.v-application code {
  background-color: #fbe5e1;
  color: black;
  padding: 0 0.4rem;
}
</style>
