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

        <h3 class="title is-3 has-text-centered">API-Key erstellen</h3>
        <div class="field has-addons has-addons-centered" v-show="!response.success">
            <div class="control">
                <input class="input" type="text" placeholder="Nutzername" v-model="username">
            </div>
            <div class="control">
                <a class="button is-info" @click="register">
                Login
                </a>
            </div>
        </div>
        
        <p class="has-text-centered has-background-danger is-3 title py-4" v-show="response.message && !response.success">
            Error: {{ response.message }}
        </p>

        <div class="field is-horizontal" v-show="response.success">
            <div class="field-label is-normal">
                <label class="label">Dein API-Key ist</label>
            </div>
            <div class="field-body">
                <div class="field has-addons">
                <p class="control is-expanded">
                    <input class="input" type="text" :value="response.message" readonly id="apikey">
                </p>
                <p class="control">
                    <button type="submit" class="button is-primary" @click="copy">Kopieren</button>
                </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            apikey: "",
            username: "",
            response: {}
        }
    },
    methods: {
        login() {
            localStorage.setItem("apikey", this.apikey)
            this.$router.push("/cards")
        },
        async register() {
            let r = await fetch("/api/user?userName=" + encodeURIComponent(this.username), {
                method: 'PUT'
            })
            this.response = await r.json()
            this.apikey = this.response.message
        },
        copy() {
            let text = document.getElementById("apikey")
            text.select();
            text.setSelectionRange(0, 99999);
            document.execCommand("copy");
        }
    }
}
</script>