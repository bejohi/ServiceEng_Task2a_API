<template>
    <div>
        <div class="level">
            <h2 class="has-text-weight-bold is-size-4">Abfrage läuft</h2>
            <router-link class="button" to="/cards/">Abfrage beenden</router-link>
        </div>

        <article class="message">
            <div class="message-header">
                <p>Deine Frage</p>
            </div>
            <div class="message-body">
                {{ question.question }}
            </div>
        </article>

        <article class="message">
            <div class="message-header">
                <p>Deine Antwort</p>
            </div>
            <div class="message-body" v-if="!answered">
                <div class="field">
                    <div class="control">
                        <textarea class="textarea" placeholder="Textarea" v-model="answer"></textarea>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <a class="button is-info" @click="answered = true">Antwort abgeben</a>
                    </div>
                </div>
            </div>
            <div class="message-body" v-else>
                <div class="field">
                    {{ answer }}
                </div>
            </div>
        </article>

        <article class="message" v-show="answered">
            <div class="message-header">
                <p>Hinterlegte Antwort</p>
            </div>
            <div class="message-body">
                <div class="field">
                    {{ question.answer }}
                </div>
            </div>
        </article>

        <article class="level" v-show="answered">
            <div class="control level-left">
                <button class="button is-link is-danger" @click="load">Falsch</button>
            </div>
            <div class="control level-right">
                <button class="button is-success" @click="levelUp">Richtig</button>
            </div>
        </article>
    </div>
</template>

<script>
export default {
    data() {
        setTimeout(() => this.load())
        return {
            question: {},
            answered: false,
            answer: ""
        }
    },
    methods: {
        async load() {
            this.answered = false
            this.answer = ""
            let r = await fetch("/api/nextCard?apikey=" + encodeURIComponent(this.$parent.apikey()))
            this.question = await r.json()
        },
        async levelUp() {
            let r = await fetch("/api/cards/" + encodeURIComponent(this.question.id) + "?apikey="+ encodeURIComponent(this.$parent.apikey()) + "&newLevel=" + encodeURIComponent(this.question.level + 1), {
                method: 'PATCH'
            })
            this.load()
        }
    }
}
</script>

Ich habe keine Fragen, danke :)