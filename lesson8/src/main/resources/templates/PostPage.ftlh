<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post=post/>
    <div class="comments">
        <div class="header">${(post.comments?size == 0)?then("No comments yet","Comments")}</div>

        <#if user??>
            <div class="comment-form">
                <form method="post" action="/post/writeComment">
                    <input type="hidden" name="postId" value="${post.id}">
                    <div>
                        <textarea id="text" name="text">${comment.text!}</textarea>
                    </div>
                    <@c.error "comment.text"/>
                    <div class="submit">
                        <input type="submit" value="Post">
                    </div>
                </form>
            </div>
        </#if>

        <#list post.comments as current>
            <div class="comment">
                <div class="username">${current.user.login}</div>
                <div class="text">${current.text}</div>
            </div>
        </#list>
    </div>
</@c.page>
