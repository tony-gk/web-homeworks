<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="4">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as user>
                <tr>
                    <td class="id"><a href="/user/${user.id}">${user.id}</a></td>
                    <td class="login">${user.login}</td>
                    <td class="creationTime">${user.creationTime}</td>
                    <td class="action">
                        <form method="post" action="/users/disable">
                            <input type="hidden" name="action" value="/users/disable">
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="hidden" name="disabledValue" value="${user.disabled?then("false","true")}">
                            <input type="submit" value="${user.disabled?then("Enable","Disable")}">
                        </form>
                    </td>
                </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
