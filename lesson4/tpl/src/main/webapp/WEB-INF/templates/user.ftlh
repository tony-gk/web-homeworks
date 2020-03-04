<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "common.ftl" as c/>

<#macro user_arrow user arrow_type="">
    <#if user.handle??>
        <a href="/user?handle=${user.handle}" class="arrow ${arrow_type}-arrow"></a>
    <#else>
        <span class="arrow-disabled ${arrow_type}-arrow"></span>
    </#if>
</#macro>

<@c.page>
    <#assign viewedUser={}/>
    <#if handle??>
        <#assign viewedUser=c.findBy(users, "handle", handle)!/>
    </#if>
    <#if user_id?? && !(viewedUser.id)??>
        <#assign viewedUser=c.findBy(users, "id", user_id?number)!/>
    </#if>

    <#if viewedUser.id??>
        <#assign prevUser=c.prevItem(users, viewedUser)!/>
        <#assign nextUser=c.nextItem(users, viewedUser)!/>
        <div class="user-box">
            <h1>
                <@c.userlink user=viewedUser nameOnly=false/>
            </h1>
            <img src="/img/user.jpg" alt="Title photo" title="Title photo">
            <ul>
                <li>id: ${viewedUser.id}</li>
                <li>handle: ${viewedUser.handle}</li>
                <li>name: ${viewedUser.name}</li>
            </ul>
        </div>

        <@user_arrow user=prevUser arrow_type="left"/>
        <@user_arrow user=nextUser arrow_type="right"/>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>

