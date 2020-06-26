<template>
    <div>
        <div class="level">
            <h2 class="has-text-weight-bold is-size-4">Deine Fragen</h2>
            <div class="level-right buttons">
                <router-link to="/cards/new" class="button is-warning">Neue Karte anlegen</router-link>
                <router-link to="/test" class="button is-info">Abfrage starten</router-link>
            </div>
        </div>

        <article class="message" v-for="item in list" :key="item.id">
            <div class="message-header">
                <p>Frage: {{ item.id }}</p>
                <p class="is-right">Level: {{ item.level }} </p>
            </div>
            <div class="message-body">
                <div class="field">
                    <h4 class="is-size-5 has-text-weight-bold is-inline">Frage:</h4> 
                    {{ item.question }}
                </div>
                <div class="field">
                    <h4 class="is-size-5 has-text-weight-bold is-inline">Antwort:</h4>
                    {{ item.answer }}
                </div>
            </div>
        </article>

    </div>
</template>

<script>
export default {
    data() {
        setTimeout(() => this.load())
        return {
            list: []
        }
    },
    methods: {
        async load() {
            let r = await fetch("/api/cards?apikey=" + encodeURIComponent(this.$parent.apikey()))
            this.list = await r.json()
        }
    }
}
</script>