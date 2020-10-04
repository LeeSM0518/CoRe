<template>
  <v-app-bar outlined app absolute flat style="background-color: white;">
    <v-img src="../../public/img/main2.png" max-width="180" class="mt-2" />
    <v-spacer></v-spacer>
    <v-text-field
      flat
      dense
      outlined
      hide-details
      prepend-inner-icon="mdi-magnify"
      label="검색"
    ></v-text-field>
    <v-spacer></v-spacer>
    <v-btn @click="pushlink('/')" icon class="mr-n2">
      <v-icon>mdi-home</v-icon>
    </v-btn>
    <v-btn icon class="mr-n2">
      <v-icon>mdi-chart-line</v-icon>
    </v-btn>
    <v-btn icon class="mr-n2">
      <v-icon>mdi-earth</v-icon>
    </v-btn>
    <v-btn icon class="mr-n2">
      <v-icon>mdi-thumb-up</v-icon>
    </v-btn>
    <v-btn
      @click="pushlink(`/profile/${$store.getters.getMemberName}`)"
      icon
      class="mr-n2"
    >
      <v-icon>mdi-account-circle</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  data: () => ({}),
  methods: {
    pushlink(link) {
      this.$router.push(link);
    },
  },
};
</script>

<style></style>
