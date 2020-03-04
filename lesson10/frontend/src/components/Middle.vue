<template>
    <div class="middle">
        <aside>
            <SidebarPost v-for="post in viewPosts" :post="post" :key="post.id"/>
        </aside>
        <main>
            <Index v-if="page === 'Index'" :posts="posts"/>
            <Users v-if="page === 'Users'"/>
            <NewPost v-if="page === 'NewPost'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Post v-if="page === 'Post'" :post="pathVar" :user="user"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import SidebarPost from './SidebarPost';
    import Users from "./middle/Users";
    import NewPost from "./middle/NewPost";
    import Post from "./middle/Post";

    export default {
        name: "Middle",
        props: ["posts", "user"],
        data: function () {
            return {
                page: "Index",
                pathVar: ""
            }
        },
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
            }
        },
        components: {
            Post,
            NewPost,
            Users,
            Index,
            Enter,
            Register,
            SidebarPost
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page, pathVar) => {
                this.page = page;
                this.pathVar = pathVar;
            });
        }
    }
</script>

<style scoped>

</style>
