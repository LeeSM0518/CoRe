<template>
  <v-col cols="10" v-if="!verified">
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
        @click="requestCode"
        ><b>{{ requestCodeMessage }}</b></v-btn
      >
    </v-form>
    <v-dialog v-model="requestCodeStatus" persistent max-width="600px">
      <EmailVerificationToResetPassword
        v-if="requestCodeStatus"
        :email="email"
        @close-EmailVerificationToResetPassword="closeEmailVerification"
        @email-verified="emailVerified"
      ></EmailVerificationToResetPassword>
    </v-dialog>
  </v-col>
  <v-col cols="10" v-else>
    <v-alert class="text-h6" type="success">
      새로운 비밀번호를<br />해당 이메일로 전송했습니다.<br />
      로그인 후, 비밀번호를<br />반드시 변경해주세요.
    </v-alert>
  </v-col>
</template>

<script>
import {
  requestCodeToEmail,
  requestToResetPassword,
} from '@/api/passwordReset.js';
import EmailVerificationToResetPassword from './EmailVerificationToResetPassword.vue';

export default {
  components: {
    EmailVerificationToResetPassword,
  },

  data: () => ({
    valid: false,
    verified: false,
    requestCodeStatus: false,
    requestCodeMessage: '이메일 인증 코드 받기',
    email: '',
    emailRules: [
      v => !!v || '이메일을 반드시 입력해주세요',
      v => /.+@.+\..+/.test(v) || '이메일 형식에 맞춰서 입력해주세요',
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

  methods: {
    closeEmailVerification() {
      this.requestCodeStatus = false;
    },
    emailVerified() {
      this.verified = true;
    },
    async requestCode() {
      const data = {
        email: this.email,
      };
      const result = await this.$store.dispatch('CALL_API', () =>
        requestCodeToEmail(data),
      );
      if (result.status === 200) {
        this.requestCodeStatus = true;
      }
    },
  },
};
</script>

<style></style>
