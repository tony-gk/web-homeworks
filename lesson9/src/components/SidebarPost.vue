<template>
    <section>
        <div class="header">
            By {{users[post.userId].login}}
        </div>
        <div class="body">
            {{post.text}}
        </div>
        <div class="footer"x>
            <a href="#" @click="goPost">View all</a>
        </div>
    </section>
</template>

<script>
    export default {
        props: ['users', 'post'],
        name: "SidebarPost",
        methods: {
            goPost: function () {
                this.$root.$emit("onChangePage", "Post", this.post);
            }
        }
    }
</script>

<style scoped>

</style>
