<#-- @ftlvariable name="userService" type="ru.itmo.wp.model.service.UserService" -->
<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.TemplateTalk[]" -->
<#-- @ftlvariable name="logged_user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="talks">
        <form action="" method="post">
            <input type="hidden" name="action" value="send"/>
            <div>
                <label for="talks_list">Select recipient:</label>
                <select id="talks_list" name="targetUserId">
                    <#list users as user>
                        <#if logged_user.id != user.id>
                            <option value="${user.id}">${user.login}</option>
                        </#if>
                    </#list>
                </select>
            </div>

            <textarea placeholder="Input your text here" name="text"></textarea>

            <div>
                <input type="submit" value="Send">
            </div>
        </form>


        <div class="datatable">
            <div class="caption">Talks</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Text</th>
                    <th>Created</th>
                </tr>
                </thead>
                <tbody>
                <#list talks as talk>
                    <tr>
                        <td>${talk.id}</td>
                        <td>${talk.sourceUserLogin}</td>
                        <td>${talk.targetUserLogin}</td>
                        <td>${talk.text}</td>
                        <td>${talk.creationTime}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>