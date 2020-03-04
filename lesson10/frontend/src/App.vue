<template>
    <!--suppress HtmlUnknownTag -->
    <body id="app">
    <Header :user="user"/>
    <Middle :posts="posts" :user="user"/>
    <Footer/>
    </body>
</template>

<script>
    import Header from './components/Header'
    import Middle from './components/Middle'
    import Footer from './components/Footer'
    import axios from 'axios'

    export default {
        name: 'app',
        data: function () {
            return {
                user: null,
                posts: []
            }
        },
        components: {
            Header,
            Middle,
            Footer
        }, beforeCreate() {
            axios.get("/api/1/posts").then(posts => this.posts = posts["data"]);

            this.$root.$on("onLogout", () => {
                alert("logout");
                localStorage.removeItem("jwt");
                this.user = null;
                axios.defaults.headers = {};
            });

            this.$root.$on("onJwt", (jwt, enter) => {
                alert("set bearer");
                axios.defaults.headers = {
                    Authorization: "Bearer " + jwt
                };

                axios.get("/api/1/users/authorized").then(response => {
                    this.user = response.data;
                    if (enter) {
                        this.$root.$emit("onEnterSuccess");
                    }
                });
            });

            this.$root.$on("onEnter", (login, password) => {
                axios.post("/api/1/jwt", {
                    login: login,
                    password: password
                }).then(response => {
                    localStorage.setItem("jwt", response.data);
                    this.$root.$emit("onJwt", response.data, true);
                }).catch(error => {
                    alert(error);
                    this.$root.$emit("onEnterValidationError", error.response.data);
                });
            });

            this.$root.$on("onRegister", (login, name, password) => {
                axios.post("/api/1/users", {
                    login: login,
                    name: name,
                    password: password
                }).then(response => {
                    localStorage.setItem("jwt", response.data);
                    this.$root.$emit("onJwt", response.data, true);
                }).catch(error => {
                    this.$root.$emit("onRegisterValidationError", error.response.data)
                });
            });
            this.$root.$on("onCreatePost", (title, text) => {
                alert(axios.defaults.headers.Authorization);
                axios.post("/api/1/posts", {
                    title: title,
                    text: text
                }).then(() => {
                    axios.get("/api/1/posts").then(posts => this.posts = posts["data"]);
                }).catch((error) => {
                    this.$root.$emit("onCreatePostValidationError", error.response.data)
                });
            });

            this.$root.$on("onCreateComment", (text, postId) => {
                axios.post("/api/1/comments", {
                    text: text,
                    postId: postId
                }).then(() => {
                    this.$root.$emit("onCommentSuccess")
                }).catch((error) => {
                    this.$root.$emit("onCreateCommentValidationError", error.response.data);
                });
            });
        }, beforeMount() {
            if (localStorage.getItem("jwt") && !this.user) {
                this.$root.$emit("onJwt", localStorage.getItem("jwt"), true);
            }
        }
    }
</script>

<style>
</style>
