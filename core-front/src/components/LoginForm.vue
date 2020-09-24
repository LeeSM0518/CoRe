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
      <v-text-field
        v-model="password"
        :rules="passwordRules"
        :type="show ? 'text' : 'password'"
        @click:append="show = !show"
        label="비밀번호"
        dense
        outlined
      ></v-text-field>
      <v-btn
        @click="login"
        block
        color="primary"
        class="mb-5"
        :disabled="!valid"
        ><b>로그인</b></v-btn
      >
    </v-form>
  </v-col>
</template>

<script>
import { requestToLogin } from '@/api/accounts.js';

export default {
  data: () => ({
    show: false,
    valid: true,
    email: '',
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

  methods: {
    async login() {
      const data = {
        email: this.email,
        password: this.password,
      };

      const result = await this.$store.dispatch('CALL_API', () =>
        requestToLogin(data),
      );
      if (result.status === 200) {
        // TODO 회원 정보 저장
        this.$router.push('/');
      }
    },
  },
};
</script>

<style></style>
