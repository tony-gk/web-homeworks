<template>
    <div>
        <article>
            <div class="title"><a href="#">{{post.title}}</a></div>
            <div class="information">By {{post.user.name}}, post id: {{post.id}}</div>
            <div class="body">
                {{post.text}}
            </div>
            <div class="footer">
                <div class="left">
                    <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    4:20
                    <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">0</a>
                </div>
            </div>
        </article>
        <div class="comments">
            <div class="header">{{post.comments.length === 0 ? "No comments yet" : "Comments"}}</div>

            <div v-if="user" class="comment-form">
                <form @submit.prevent="onCreateComment">
                    <div>
                        <textarea id="text" v-model="commentInput"/>
                    </div>
                    <div class="error">{{error}}</div>
                    <div class="submit">
                        <input type="submit" value="Send">
                    </div>
                </form>
            </div>

            <div class="comment" v-for="comment in post.comments" :key="comment.id">
                <div class="username">{{comment.user.name}}</div>
                <div class="text">{{comment.text}}</div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Post",
        props: ['post', 'user'],
        data: function () {
            return {
                commentInput: "",
                error: ""
            }
        },
        beforeCreate() {
            this.$root.$on("onCreateCommentValidationError", (error) => this.error = error)

            this.$root.$on("onCommentSuccess", () => {
                this.commentInput = "";
                axios.get("/api/1/comments", {
                    params: {
                        postId: this.post.id
                    }
                }).then((response) => this.post.comments = response.data)
            })
        },
        methods: {
            onCreateComment: function () {
                this.$root.$emit("onCreateComment", this.commentInput, this.post.id);
            }
        }
    }
</script>

<style scoped>

    article {
        margin-bottom: 2em;
    }

    article .title {
        color: var(--caption-color);
        font-weight: bold;
        font-size: 1.25rem;
    }

    article .title a {
        text-decoration: none;
    }

    article .information {
        margin-top: 0.25rem;
        font-size: 0.85rem;
        color: #888;
        margin-bottom: 0.6rem;
    }

    article .body {
        border-left: 4px solid var(--border-color);
        padding-left: 0.75rem;
        font-family: "helvetica neue", helvetica, arial, sans-serif;
        margin-bottom: 0.5rem;
    }

    article .body p:last-child {
        margin: 0;
    }

    article .footer {
        border: 1px solid var(--border-color);
        border-radius: var(--border-radius);
        overflow: hidden;
        padding: 0.1rem;
        margin-top: 0.25rem;
    }

    article .footer .left {
        float: left;
        padding-left: 0.5rem;
    }

    article .footer .left img {
        position: relative;
        top: 5px;
    }

    article .footer .right img {
        position: relative;
        margin-left: 0.5rem;
        top: 2px;
    }

    article .footer .right {
        float: right;
        font-size: 0.85rem;
        line-height: 2rem;
        padding-right: 0.5rem;
    }

    article .footer .positive-score {
        color: green;
        font-weight: bold;
        line-height: 1.75rem;
    }

    .comments .header {
        color: var(--caption-color);
        font-weight: bold;
        font-size: 1.4rem;
        margin-bottom: 1rem;
    }

    .comment-form {
        padding: 0.5rem;
        border: 1px solid var(--border-color);
        border-radius: var(--border-radius);
    }

    .comments textarea {
        min-height: 5rem;
        min-width: 30rem;
        padding-left: 0.2rem;
    }

    .comments .submit {
        margin-top: 0.5rem;
    }

    .comments .error {
        color: var(--error-color);
        font-size: 0.75rem;
    }

    .comments .comment {
        padding-left: 1rem;
        margin: 2rem 0;
    }

    .comment .username {
        border-bottom: 1px solid var(--border-color);
        font-size: 1.2rem;
        color: blue;
        font-weight: bold;
        padding-bottom: 0.2rem;
    }

    .comment .text {
        padding-left: 0.5rem;
        padding-top: 0.2rem;
    }
</style>