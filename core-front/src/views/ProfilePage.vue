<template>
  <v-container>
    <v-row align="start">
      <v-col cols="3" align-self="start">
        <v-img
          :src="profileImage"
          max-width="150"
          max-height="150"
          class="mt-2"
          style="border-radius: 50%"
        />
      </v-col>
      <v-col cols="9">
        <p class="text-h5">{{ this.$route.params.name }}</p>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    profileImage: process.env.VUE_APP_STATIC_URL + 'nalsm98.jpeg',
  }),

  methods: {
    getProfileImagePath() {
      const memberName = this.$store.getters.getMemberName();
      return process.env.VUE_APP_STATIC_URL + '.jpeg';
    },
  },
};
</script>

<style></style>
