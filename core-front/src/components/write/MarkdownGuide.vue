<template>
  <v-row>
    <v-col cols="6">
      <v-textarea
        label="내용"
        auto-grow
        outlined
        rows="30"
        row-height="15"
        readonly
        v-model="example"
      ></v-textarea>
    </v-col>
    <v-col cols="6">
      <v-card>
        <div id="previewExample" class="markdown-body pa-5"></div>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { md } from '@/utils/markdown.js';

export default {
  data: () => ({
    example: `# 제목 1

## 제목 2

### 제목 3

#### 제목 4

##### 제목 5

###### 제목 6

*이텔릭체*

**두껍게**

~~취소선~~

<u>밑줄</u>

1. 순서 있는 목차1
2. 순서 있는 목차2

* 순서 없는 목차1 
* 순서 없는 목차2
  * 순서 없는 목차3

[링크](#)

\`\`\`java
// 코드 블록
int a = 3;
\`\`\`

> 인용문

\`강조\`

| 테이블A | 테이블B | 테이블C |
| --- | --- | --- |
| 1 | 2 | 3 |`,
  }),

  mounted() {
    document.getElementById('previewExample').innerHTML = md.render(`# 제목 1

## 제목 2

### 제목 3

#### 제목 4

##### 제목 5

###### 제목 6

*이텔릭체*

**두껍게**

~~취소선~~

<u>밑줄</u>

1. 순서 있는 목차1
2. 순서 있는 목차2

* 순서 없는 목차1 
* 순서 없는 목차2
  * 순서 없는 목차3

[링크](#)

\`\`\`java
// 코드 블록
int a = 3;
\`\`\`

> 인용문

\`강조\`

| 테이블A | 테이블B | 테이블C |
| --- | --- | --- |
| 1 | 2 | 3 |`);
  },
};
</script>

<style></style>
