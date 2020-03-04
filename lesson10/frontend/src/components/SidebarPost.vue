<template>
    <section>
        <div class="header">
            By {{post.user.name}}
        </div>
        <div class="body">
            {{post.text}}
        </div>
        <div class="footer">
            <a href="#" @click="goPost(post)">View all</a>
        </div>
    </section>

</template>

<script>
    export default {
        props: ['post'],
        name: "SidebarPost",
        methods: {
            goPost: function (post) {
                this.$root.$emit("onChangePage", "Post", post);
            }
        }
    }
</script>

<style scoped>

</style>
