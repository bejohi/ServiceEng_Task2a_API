<template>
    <div>
        <article class="message">
            <div class="message-header">
                <p>Deine Frage</p>
            </div>
            <div class="message-body">
                {{ Question }}
            </div>
        </article>

        <article class="message">
            <div class="message-header">
                <p>Deine Antwort</p>
            </div>
            <div class="message-body">
                <div class="field">
                    <div class="control">
                        <textarea class="textarea" placeholder="Textarea"></textarea>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <a class="button is-info" @click="answered = true">Antwort abgeben</a>
                    </div>
                </div>
            </div>
        </article>

        <article class="message" v-show="answered">
            <div class="message-header">
                <p>Hinterlegte Antwort</p>
            </div>
            <div class="message-body">
                <div class="field">
                    {{ Answer }}
                </div>
                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-success">Richtig</button>
                    </div>
                    <div class="control">
                        <button class="button is-link is-danger">Falsch</button>
                    </div>
                </div>
            </div>
        </article>
    </div>
</template>

<script>
export default {
    data() {
        return {
            Question: "Was ist 1+1",
            Answer: "2",
            answered: false
        }
    }
}
</script>

Ich habe keine Fragen, danke :)