<template>
  <v-app>
    <router-view name="header"></router-view>
    <v-main>
      <v-container fill-height fluid style="background: #FAFAFA">
        <router-view name="body"></router-view>
      </v-container>
    </v-main>
    <v-dialog
      v-model="$store.state.apiLoading"
      hide-overlay
      persistent
      width="300"
    >
      <v-card color="primary" dark>
        <v-card-text>
          잠시만 기다려주세요
          <v-progress-linear
            indeterminate
            color="white"
            class="mb-0"
          ></v-progress-linear>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-dialog width="500" v-model="$store.state.apiError">
      <v-alert prominent type="error" class="mb-0">
        <v-row align="center">
          <v-col>{{ $store.state.apiErrorMessage }}</v-col>
          <v-col class="shrink">
            <v-btn @click="$store.commit('invisibleApiError')">확인</v-btn>
          </v-col>
        </v-row>
      </v-alert>
    </v-dialog>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {};
  },
};
</script>
