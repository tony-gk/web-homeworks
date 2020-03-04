<#-- @ftlvariable name="userInfo" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if userInfo??>
        <p>id: ${userInfo.id}</p>
        <p>login: ${userInfo.login}</p>
        <p>created: ${userInfo.creationTime}</p>
        <p>disabled: ${userInfo.disabled?then("true", "false")}</p>
    <#else>
        <h3>No such user</h3>
    </#if>
</@c.page>