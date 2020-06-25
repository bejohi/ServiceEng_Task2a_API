<template>
    <div>
        <h3 class="title is-3 has-text-centered">
            Hier ist Frage Nr. {{ $route.params.id }}
        </h3>

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
                <div class="field">
                    <div class="control">
                        <a class="button is-info" @click="save">Änderungen speichern</a>
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
                <div class="field">
                    <div class="control">
                        <a class="button is-info" @click="save">Änderungen speichern</a>
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
            Answer: "2"
        }
    }
}
</script>