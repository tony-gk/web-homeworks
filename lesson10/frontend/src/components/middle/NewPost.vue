<template>
    <div class="article-form">
        <form @submit.prevent="onCreatePost">
            <div class="title">

                <label for="title">
                    Title:
                </label>
                <input id="title" v-model="title">
            </div>
            <label>
                <textarea rows="10" cols="50" style="resize:none" v-model="text"/>
            </label>

            <div class="error">{{error}}</div>
            <div><input type="submit" value="Create"></div>

        </form>
    </div>
</template>

<script>
    export default {
        name: "NewPost",
        data: function () {
            return {
                title: "",
                text: "",
                error: ""
            }
        },
        beforeCreate() {
            this.$root.$on("onCreatePostValidationError", (error) => {
                this.error = error
            })
        },
        methods: {
            onCreatePost: function () {
                this.$root.$emit("onCreatePost", this.title, this.text);
            }
        }
    }
</script>

<style scoped>
    .article-form div {
        margin: 1rem 0;
    }
    .error {
        font-size: 0.9rem;
        color: var(--error-color);
        margin-left: 12rem;
    }
</style>