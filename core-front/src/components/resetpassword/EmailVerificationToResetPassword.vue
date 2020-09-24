<template>
  <v-card>
    <v-card-title>
      <span class="text-h6">이메일 인증</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <v-row>
          <v-col>
            <v-alert class="text-h6" type="warning">
              {{ countMessage }}
            </v-alert>
          </v-col>
        </v-row>
        <v-row v-if="!fail">
          <v-text-field
            v-model="code"
            :rules="codeRules"
            label="인증 코드"
            dense
            outlined
          ></v-text-field>
          <v-btn
            @click="requestVerificate"
            block
            color="primary"
            :disabled="!validCode"
            class="mb-n5"
            ><b>인증</b></v-btn
          >
        </v-row>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-1" text @click="close">Close</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { requestToResetPassword } from '@/api/passwordReset.js';

var countdown = null;

export default {
  props: ['email'],
  data: function() {
    return {
      countMessage: '인증 가능 시간:  60 (초)',
      countView: 60,
      codeRules: [
        v => !!v || '인증 코드를 반드시 입력해주세요',
        v => /^[0-9]*$/.test(v) || '인증 코드는 숫자만 가능합니다',
      ],
      code: '',
      fail: false,
    };
  },

  methods: {
    close() {
      this.$emit('close-emailVerification');
    },
    countDown() {
      if (this.countView > 0) {
        this.countView--;
        this.countMessage = '인증 가능 시간:  ' + this.countView + ' (초)';
      } else {
        this.countMessage = '이메일 인증을 재요청해 주시길 바랍니다.';
        this.fail = true;
        clearInterval(countdown);
      }
    },
    excuteCount() {
      countdown = setInterval(this.countDown, 1000);
    },
    async requestVerificate() {
      const data = {
        email: this.email,
        code: this.code,
      };
      const result = await this.$store.dispatch('CALL_API', () =>
        requestToResetPassword(data),
      );
      if (result.status === 200) {
        this.$emit('email-verified');
        this.close();
      }
    },
  },

  computed: {
    validCode() {
      if (!!this.code && /^[0-9]*$/.test(this.code)) return true;
      else return false;
    },
  },

  created() {
    this.excuteCount();
  },

  destroyed() {
    clearInterval(countdown);
  },
};
</script>

<style></style>
