<template>
  <v-row align="center" justify="center">
    <v-card outlined width="400">
      <v-row justify="center">
        <v-img
          src="../../public/img/main2.png"
          max-width="348"
          class="mr-3 mt-5"
        />
        <v-card-title class="text-subtitle-1 grey--text text--darken-1 mt-n5"
          ><b>새로운 비밀번호를 이메일로 전송해드립니다.</b></v-card-title
        >
        <PasswordResetForm></PasswordResetForm>
      </v-row>
      <v-row class="mt-n5" justify="center">
        <v-col cols="4">
          <v-divider class="mt-3"></v-divider>
        </v-col>
        <v-col cols="1">
          <v-row justify="center">
            <b class="grey--text">또는</b>
          </v-row>
        </v-col>
        <v-col cols="4">
          <v-divider class="mt-3"></v-divider>
        </v-col>
      </v-row>
      <v-row justify="center">
        <router-link to="login" class="primary--text mb-2 text-button"
          ><b>로그인</b></router-link
        >
      </v-row>
    </v-card>
  </v-row>
</template>

<script>
import PasswordResetForm from '@/components/resetpassword/PasswordResetForm.vue';

export default {
  components: {
    PasswordResetForm,
  },
};
</script>

<style></style>
