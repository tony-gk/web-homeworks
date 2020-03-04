<template>
    <div class="middle">
        <Sidebar :posts="posts" :users="users"/>
        <main>
            <Index v-if="page === 'Index'" :users="users" :posts="posts"/>
            <Post v-if="page === 'Post'" :post="pathVariable" :users="users" :comments="comments"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <AddPost v-if="page === 'AddPost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Users v-if="page === 'Users'" :users="users"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import AddPost from './middle/AddPost';
    import EditPost from "./middle/EditPost";
    import Sidebar from "./Sidebar";
    import Users from "./middle/Users";
    import Post from "./middle/Post";

    export default {
        name: "Middle",
        props: ['users', 'posts', 'comments'],
        data: function () {
            return {
                page: "Index",
                pathVariable: ""
            }
        },
        components: {
            Post,
            Users,
            Sidebar,
            EditPost,
            Index,
            Enter,
            Register,
            AddPost
        },
        beforeCreate() {
            this.$root.$on("onChangePage", (page, pathVariable) => {
                this.pathVariable = pathVariable;
                this.page = page;
            });
        }
    }
</script>

<style scoped>

</style>
