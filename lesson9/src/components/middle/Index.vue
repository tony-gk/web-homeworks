<template>
    <div>
        <PostTemplate v-for="post in viewPosts" :key="post.id" :post="post" :author="users[post.userId]"/>
    </div>
</template>

<script>
    import PostTemplate from "../templates/PostTemplate";

    export default {
        name: "Index",
        components: {PostTemplate},
        props: ['posts', 'users'],
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id);
            }
        }
    }
</script>

<style scoped>
</style>
