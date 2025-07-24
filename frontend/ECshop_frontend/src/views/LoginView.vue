<template>
  <div class="login-container">
    <h2>登入系統</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="username">帳號：</label>
        <input type="text" v-model="username" id="username" required />
      </div>
      <div>
        <label for="password">密碼：</label>
        <input type="password" v-model="password" id="password" required />
      </div>
      <button type="submit">登入</button>
      <br/>
      <router-link to="/register">點此註冊</router-link>

    </form>
    <div v-if="message" class="message">{{ message }}</div>
  </div>
</template>

<script>

import { login } from '../API';
export default {
  name: 'LoginView',
  data() {
    return {
      username: '',
      password: '',
      message: '',
    };
  },
  methods: {
    async handleLogin() {
      if (!this.username || !this.password) {
        this.message = '請輸入帳號與密碼';
        return;
      }

      try {
        const response = await login(this.username, this.password);

        const token = response.data.token;
        const userId = response.data.userId;
        const groupId = response.data.groupID;
        // console.error(response.data);
        localStorage.setItem('token', token);
        localStorage.setItem('userID', userId);
        localStorage.setItem('groupID', groupId);
        this.$root.currentGroupID = groupId;

        if (response.data && response.data.status === 200) {
          // ✅ 不需要再用 localStorage 儲存 token
        console.error(response.data)
          if(response.data.groupID=="1"){
            this.$router.push('/home'); // 導向首頁
          }else if(response.data.groupID=="2"){
            this.$router.push('/product');
          }

        } else {
          this.message = response.data.message || '登入失敗';
        }
      } catch (error) {
        console.error(error);
        this.message = '登入失敗，請稍後再試';
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  max-width: 300px;
  margin: 80px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
.login-container div {
  margin-bottom: 15px;
}
.message {
  margin-top: 15px;
  color: green;
}
</style>
