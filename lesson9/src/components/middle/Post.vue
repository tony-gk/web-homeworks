<template>
    <div>
        <PostTemplate :author="users[post.userId]" :post="post"/>
        <div class="comments">
            <div class="header">{{viewComments.length === 0 ? "No comments yet" : "Comments"}}</div>

            <div class="comment" v-for="comment in viewComments" :key="comment.id">
                <div class="username">{{users[comment.userId].login}}</div>
                <div class="text">{{comment.text}}</div>
            </div>
        </div>
    </div>
</template>

<script>
    import PostTemplate from "../templates/PostTemplate";

    export default {
        name: "Post",
        components: {PostTemplate},
        props: ['post', 'users', 'comments'],
        computed: {
            viewComments: function () {
                return Object.values(this.comments)
                    .filter(c => c.postId === this.post.id)
                    .sort((a, b) => b.id - a.id);
            }
        }
    }
</script>

<style scoped>
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