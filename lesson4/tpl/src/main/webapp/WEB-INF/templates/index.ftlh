<#import "common.ftl" as c/>

<@c.page>
    <#list posts as post>
        <@c.displayPost postId=post.id cropText=true>
        </@c.displayPost>
    </#list>
</@c.page>
