<template>
  <div id="app">
    <!-- 登入頁面不顯示 nav -->
    <nav v-if="!isLoginPage && groupID === '1'">
      <router-link to="/home">選購產品</router-link>
      <router-link to="/about">我的訂單管理</router-link>

    </nav>
    <nav v-if="!isLoginPage && groupID === '2'">
      <router-link to="/product">管理產品</router-link>
    </nav>
    <br />
    <router-view />
  </div>
</template>
<script>
export default {
  data() {
    return {
      currentGroupID: localStorage.getItem("groupID"),
    };
  },
  computed: {
    isLoginPage() {
      return this.$route.name === 'LoginView' || this.$route.path === '/';
    },
    groupID() {
      return this.currentGroupID;
    }
  },
  created() {
    window.addEventListener("storage", this.syncGroupID);
  },
  methods: {
    syncGroupID() {
      this.currentGroupID = localStorage.getItem("groupID");
    }
  },
  beforeUnmount() {
    window.removeEventListener("storage", this.syncGroupID);
  }
};
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #ddd;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  margin: 0 15px;
  text-decoration: none;
  transition: color 0.3s;
}

nav a.router-link-exact-active {
  color: #42b983;
}

nav a:hover {
  color: #42b983;
}
</style>
