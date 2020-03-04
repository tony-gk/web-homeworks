<#-- @ftlvariable name="loggedAsAdmin" type="java.lang.Boolean" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if loggedAsAdmin>
                    <th>Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if loggedAsAdmin>
                        <td class="user_admin"><a href="#"></a></td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            let $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            ajax({
                data: {
                    action: "findAll"
                },
                success: function (response) {

                    response["users"].forEach(function (user) {
                        $noData.hide();
                        let $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        $tr.find("tr").attr("data-userId", user["id"]);
                        <#if loggedAsAdmin>
                        $tr.find(".user_admin a").text(user["admin"] ? "disable" : "enable");
                        </#if>
                        $(".users tbody").append($tr)
                    });
                    $(".users tr").click(function () {
                        ajax({
                            data: {
                                action: "findUser",
                                userId: $(this).attr("data-userId")
                            },
                            success: function (response) {
                                notify("Creation time: " + response["user"].creationTime);
                            },
                        })
                    });
                    $(".users a").click(function (event) {
                        event.stopPropagation();
                        let $context = $(this);
                        let userId = $(this).closest('tr').attr("data-userId");
                        let adminValue = $(this).text() === "enable";
                        ajax({
                            data: {
                                action: "setAdmin",
                                userId: userId,
                                adminValue: adminValue
                            },
                            success: function (response) {
                                if (response["error"]) {
                                    notifyError(response["error"]);
                                } else {
                                    $context.text(adminValue ? "disable" : "enable");
                                }
                            }
                        });
                        return false;
                    })
                },
            });
        });


    </script>
</@c.page>
