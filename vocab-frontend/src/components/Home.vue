<template>
    <div style="margin-top: 5rem;">
        <h3 class="title is-3 has-text-centered">Mit API-Key einloggen</h3>
        <div class="field has-addons has-addons-centered">
            <div class="control">
                <input class="input" type="text" placeholder="API-Key" v-model="apikey">
            </div>
            <div class="control">
                <a class="button is-info" @click="login">
                Login
                </a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            apikey: ""
        }
    },
    methods: {
        login() {
            localStorage.setItem("apikey", this.apikey)
            this.$router.push("/test")
        }
    }
}
</script>