<template>
<div class="body">
      <h1 class="title is-1 has-text-centered" style="margin-top:1rem;">
        Vocab
      </h1>
    <router-view class="container"></router-view>
    <hr v-if="apikey()">
    <div class="field is-grouped" v-if="apikey()">
      <div class="control">
        Du bist eingelogged als <i>{{ apikey() }}</i> -
        <a @click="logout">Ausloggen</a>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
import Home from "./components/Home.vue";
import Test from "./components/Test.vue";
import Cards from "./components/Cards.vue";
import Card from "./components/Card.vue";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    { path: "/", component: Home },
    { path: "/test", component: Test },
    { path: "/cards", component: Cards },
    { path: "/cards/new", component: Card }
  ]
});

export default {
  router,
  methods: {
    apikey() {
      return localStorage.getItem("apikey") ? localStorage.getItem("apikey") : ""
    },
    logout() {
      localStorage.removeItem("apikey");
      router.push("/")
    }
  }
};
</script>