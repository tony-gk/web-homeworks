<template>
    <div>
        <article v-for="post in viewPosts" :post="post" :key="post.id">
            <div class="title"><a href="#" @click="goPost(post)">{{post.title}}</a></div>
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
                    <a href="#">{{post.comments.length}}</a>
                </div>
            </div>
        </article>
    </div>
</template>

<script>
    export default {
        name: "Index",
        props: ['posts'],
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id);
            }
        },
        methods: {
            goPost: function (post) {
                this.$root.$emit("onChangePage", "Post", post);
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
</style>
