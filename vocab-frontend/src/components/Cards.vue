<template>
    <div>
        <h3 class="title is-3 has-text-centered">Deine Fragen</h3>
        <div class="content">
            <ul>
                <li v-for="item in list" :key="item.id">{{item.Question}} - <router-link :to="'cards/' + item.id">Bearbeiten</router-link> </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            list: [
                { id: 1, Question: "Wer is Cece?", Answer: "Jess Freundin", Level: 5 },
                { id: 1, Question: "Wer is Cece?", Answer: "Jess Freundin", Level: 5 },
            ]
        }
    }
}
</script>