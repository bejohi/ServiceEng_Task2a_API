<template>
    <div>
        <h3 class="title is-3 has-text-centered">
            Erstelle eine weitere Frage:
        </h3>

        <article class="message is-success" v-show="res">
            <div class="message-body">
                <p class="content">
                    {{res}}
                </p>
            </div>
        </article>

        <article class="message is-danger" v-show="err">
            <div class="message-body">
                <p class="content">
                    {{err}}
                </p>
            </div>
        </article>

        <article class="message">
            <div class="message-header">
                <p>Deine Frage</p>
            </div>
            <div class="message-body">
                <div class="field">
                    <div class="control">
                        <textarea class="textarea" placeholder="Textarea" v-model="Question"></textarea>
                    </div>
                </div>
            </div>
        </article>

        <article class="message">
            <div class="message-header">
                <p>Deine Antwort</p>
            </div>
            <div class="message-body">
                <div class="field">
                    <div class="control">
                        <textarea class="textarea" placeholder="Textarea" v-model="Answer"></textarea>
                    </div>
                </div>
            </div>
        </article>

        <article class="level">                
            <div class="field level-left">
                <div class="control">
                    <router-link class="button is-info" to="/cards">Zurück</router-link>
                </div>
            </div>
            <div class="field level-right">
                <div class="control">
                    <a class="button is-success" @click="save">Karte speichern</a>
                </div>
            </div>
        </article>
    </div>
</template>

<script>
export default {
    data() {
        return {
            Question: "",
            Answer: "",
            res: ""
        }
    },
    methods: {
        async save() {
            this.res = this.err = ""
            let r = await fetch("http://localhost:8080/cards?apikey="+ encodeURIComponent(this.$parent.apikey()), {
                method: 'PUT',
                    headers: {
                    'Content-Type': 'application/json'
                    },
                body: JSON.stringify({"question": this.Question, "answer": this.Answer})
            })
            if (r.ok){
                this.Answer = this.Question = ""
                this.res = await r.text()
            }
            else
            this.err = r.status + " - " + r.statusText
        }
    }
}
</script>