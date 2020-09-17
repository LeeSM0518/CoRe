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
        v-if="!verified"
        block
        color="primary"
        class="mb-5"
        :disabled="!validEmail"
        @click="requestCode"
        ><b>{{ requestCodeMessage }}</b></v-btn
      >
      <template v-if="verified">
        <v-text-field
          v-model="password"
          :rules="passwordRules"
          type="password"
          label="비밀번호"
          dense
          outlined
        ></v-text-field>
        <v-text-field
          v-model="name"
          :rules="nameRules"
          label="닉네임(별명)"
          :counter="10"
          dense
          outlined
        ></v-text-field>
        <v-combobox
          v-model="tags"
          :items="items"
          :search-input.sync="search"
          hide-selected
          label="관심사를 선택해주세요"
          multiple
          :rules="tagRules"
          persistent-hint
          small-chips
          class="mt-3 mb-10"
        >
          <template v-slot:no-data class="mt-3 mb-5">
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  일치하는 태그가 존재하지 않습니다. <br />새로운 태그를
                  등록하려면 <kbd>Enter</kbd> 를 눌러주세요.
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-combobox>
        <v-btn
          @click="signUp"
          block
          color="primary"
          class="mb-5"
          :disabled="!valid"
          ><b>회원가입</b></v-btn
        >
      </template>
    </v-form>
    <v-dialog v-model="requestCodeStatus" persistent max-width="600px">
      <EmailVerification
        v-if="requestCodeStatus"
        :email="email"
        @close-emailVerification="closeEmailVerification"
        @email-verified="emailVerified"
      ></EmailVerification>
    </v-dialog>
  </v-col>
</template>

<script>
import { requestCodeToEmail, requestToSignUp } from '@/api/accounts.js';
import { getListByName } from '@/api/hashtags.js';
import EmailVerification from './EmailVerification.vue';

export default {
  components: {
    EmailVerification,
  },

  data: () => ({
    items: [],
    tags: [],
    search: null,
    dialog: false,
    verified: false,
    requestCodeStatus: false,
    requestCodeMessage: '이메일 인증 코드 받기',
    show: false,
    valid: false,
    email: '',
    name: '',
    nameRules: [
      v => !!v || '닉네임을 반드시 입력해주세요.',
      v => v.length <= 10 || '닉네임은 10글자 이하만 가능합니다',
    ],
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
    tagRules: [
      v => v.length >= 1 || '관심사를 최소 1개 이상 선택해주세요.',
      v => {
        for (var i = 0; i < v.length; i++) {
          if (/[\s]/.test(v[i])) return '태그에 공백을 기입할 수 없습니다.';
        }
        return true;
      },
      // !/[\s]/.test(v) || '태그에 공백을 기입할 수 없습니다.',
      v => {
        for (var i = 0; i < v.length; i++) {
          if (/[\[\]\\\;\.\,\/'"=~!@#$%^&*()_+|<>?:{}]/.test(v[i]))
            return '태그에 특수 문자를 기입할 수 없습니다.';
        }
        return true;
      },
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

  watch: {
    search: function(newSearch, oldSearch) {
      this.getHashtags();
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
    async getHashtags() {
      var blank_pattern = /[\s]/;
      var special_pattern = /[\[\]\\\;\.\,\/'"=~!@#$%^&*()_+|<>?:{}]/gi;
      try {
        this.items = [];
        if (
          !!this.search &&
          blank_pattern.test(this.search) != true &&
          special_pattern.test(this.search) != true
        ) {
          const { data } = await getListByName(this.search);
          data.forEach(element => {
            this.items.push(element.name);
          });
        }
      } catch (error) {}
    },
    async signUp() {
      const data = {
        email: this.email,
        name: this.name,
        password: this.password,
        interests: this.tags,
      };
      // TODO 없는 해시태그로 회원가입 시 해시태그 생성 로직 구현
      const result = await this.$store.dispatch('CALL_API', () =>
        requestToSignUp(data),
      );
      if (result.status === 200) {
        this.$router.push('/login');
      }
      console.log(result);
    },
  },
};
</script>

<style></style>
