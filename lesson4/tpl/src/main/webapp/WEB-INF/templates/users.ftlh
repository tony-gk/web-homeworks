<#import "common.ftl" as c/>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Handle</th>
                <th>Name</th>
                <th>Id</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <th>
                        <#if user?? && u.id == user.id>*
                        </#if>
                        <@c.userlink user=u nameOnly=false/>
                    </th>
                    <th>${u.name}</th>
                    <th>${u.id}</th>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
