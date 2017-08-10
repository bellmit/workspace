<template>
  <div class="login-form-wrapper">
    <form class="form-signin" @submit.prevent="validateBeforeSubmit">
      <div class="logo text-center">
        <h2 class="form-signin-heading">请登录</h2>
      </div>
      <div class="login-box">
        <input type="text" class="form-control account" placeholder="账号"
               @input="changed()"
          name="userAccount"
          v-model="registerObj.userAccount"
          v-validate:userAccount.initial="'required'"
          :class="{ 'is-danger': errors.has('userAccount') }" autofocus>
          <transition name="valid-help">
            <div v-show="errors.has('userAccount')" :class="{ 'valid-alert': errors.has('userAccount') }">
              <span class="help is-danger">{{ errors.first('userAccount') }}</span>
            </div>
          </transition>
        <input type="password" class="form-control password" placeholder="密码"
               @input="changed()"
          name="userPassword"
          v-model="registerObj.userPassword"
          v-validate:userPassword.initial="'required'"
          :class="{ 'is-danger': errors.has('userPassword') }">
          <transition name="valid-help">
            <div v-show="errors.has('userPassword')" :class="{ 'valid-alert': errors.has('userPassword') }">
              <span class="help is-danger">{{ errors.first('userPassword') }}</span>
            </div>
          </transition>


          <div v-show="invalidAccountOrPassword" :class="{ 'valid-alert': invalidAccountOrPassword }">
            <p class="help is-danger">用户名或密码不对</p>
          </div>

          <button class="el-button btn-login el-button--primary">登录</button>
      </div>
    </form>
  </div>
</template>

<script>

  import Vue from 'vue';

  import messages from './locale/zh_CN';
  import VeeValidate, {
    Validator
  } from 'vee-validate';

  Vue.use(VeeValidate);

  // To prevent the errors from showing up before the user has typed anything we can use v-validate.initial instead of v-validate.
  Validator.updateDictionary({
    zh_CN: {
      messages: Object.assign({}, messages, {
        required: (field) => {
          return `请输入${field}`
        }
      }),
      attributes: {
        userAccount: '账号',
        userPassword: '密码'
      }
    }
  });

export default {
  name: 'login',
  created() {
      // 组件创建完后获取数据，
      // 此时 data 已经被 observed 了
      this.$validator.setLocale('zh_CN');
    },
  mounted() {

  },
  data() {
    return {
      registerObj: {
        userAccount: null,
        userPassword: null
      },
      invalidAccountOrPassword: false
    };
  },
  methods: {
    changed() {
        this.invalidAccountOrPassword = false;
    },
    validateBeforeSubmit() {

        // Validate All returns a promise and provides the validation result.
        this.$validator.validateAll().then(success => {
          if (!success) {
            // handle error
            this.invalidAccountOrPassword = true;

            return;
          }

          //this.register();
        }).catch(() => {
          // It has failed.

        });
      }
  }
}

</script>

<style lang="scss">
  body {
    background: url(../assets/images/login-bg.jpg) no-repeat;
    background-position: center;
  }

  .login-form-wrapper {
    width: 390px;
    margin: 148px auto;
  }

  .form-signin .form-signin-heading {
    margin: 0 auto;
    text-indent: -9999px;
    width: 243px;
    height: 146px;
    background: url(../assets/images/login-logo.png) no-repeat center center;
    background-size: 100% 100%;
  }

  .login-box {
    width: 100%;
    margin-top: 60px;
    padding: 45px 40px 58px;
    border-radius: 2px;
    background: #fff;
    box-sizing: border-box;
    .form-control {
      width: 100%;
      margin: 0 auto 20px;
      padding-left: 26px;
      line-height: 36px;
      border: none;
      border-bottom: 1px solid #ced4d9;
      box-sizing: border-box;
      outline: 0;
    }
    .account {
      background: url(../assets/images/login/icon-account.png) no-repeat left center;
    }
    .password {
      background: url(../assets/images/login/icon-password.png) no-repeat left center;
    }
    .btn-login {
      width: 100%;
      height: 42px;
      display: block;
      margin-top: 40px;
    }
  }
</style>
