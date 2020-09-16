<template>
  <v-col cols="10">
    <v-form v-model="valid">
      <v-text-field
        v-model="email"
        :rules="emailRules"
        label="이메일"
        dense
        outlined
      ></v-text-field>
      <v-btn
        block
        color="primary"
        class="mb-5"
        :disabled="!validEmail"
        @click="requestCode = true"
        ><b>이메일 인증 코드 받기</b></v-btn
      >
      <template v-if="requestCode">
        <v-text-field
          v-model="code"
          :rules="codeRules"
          label="인증 코드"
          dense
          outlined
        ></v-text-field>
        <v-btn block color="primary" class="mb-5" :disabled="!validCode"
          ><b>인증</b></v-btn
        >
      </template>
      <template v-if="certified">
        <v-text-field
          v-model="password"
          :rules="passwordRuless"
          :type="show ? 'text' : 'password'"
          @click:append="show = !show"
          label="비밀번호"
          dense
          outlined
        ></v-text-field>
        <v-btn block color="primary" class="mb-5" :disabled="!valid"
          ><b>회원가입</b></v-btn
        >
      </template>
    </v-form>
  </v-col>
</template>

<script>
export default {
  data: () => ({
    certified: false,
    requestCode: false,
    show: false,
    valid: false,
    email: '',
    codeRules: [
      v => !!v || '인증 코드를 반드시 입력해주세요',
      v => /^[0-9]*$/.test(v) || '인증 코드는 숫자만 가능합니다',
    ],
    code: '',
    emailRules: [
      v => !!v || '이메일을 반드시 입력해주세요',
      v => /.+@.+\..+/.test(v) || '이메일 형식에 맞춰서 입력해주세요',
    ],
    password: '',
    passwordRules: [
      v => !!v || '비밀번호를 반드시 입력해주세요',
      v => v.length >= 8 || '최소 8 글자 이상 입력해주세요.',
    ],
  }),
  computed: {
    validEmail() {
      if (!!this.email && /.+@.+\..+/.test(this.email)) return true;
      else return false;
    },
    validCode() {
      if (!!this.code && /^[0-9]*$/.test(this.code)) return true;
      else return false;
    },
  },
};
</script>

<style></style>
