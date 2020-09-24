<template>
  <v-row align="center" justify="center">
    <v-card outlined width="400">
      <v-row justify="center">
        <v-img
          src="../../public/img/main2.png"
          max-width="348"
          class="mr-3 mt-5"
        />
        <LoginForm></LoginForm>
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
        <router-link to="signup" class="primary--text mb-2 text-button"
          ><b>회원가입</b></router-link
        >
      </v-row>
      <v-row justify="center">
        <router-link to="password" class="primary--text mb-5 text-button"
          ><b>비밀번호 찾기</b></router-link
        >
      </v-row>
    </v-card>
  </v-row>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue';

export default {
  components: {
    LoginForm,
  },
};
</script>

<style></style>
