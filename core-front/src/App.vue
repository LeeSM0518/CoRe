<template>
  <v-app>
    <router-view name="header"></router-view>
    <v-main>
      <v-container fill-height fluid style="background: #FAFAFA">
        <router-view name="body"></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
};
</script>
