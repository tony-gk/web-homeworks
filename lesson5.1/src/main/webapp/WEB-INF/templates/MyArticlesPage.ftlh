<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">Articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hidden</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <#assign submitValue = article.hidden?then("Show", "Hide")>
                <tr>
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <td>
                        <form method="post" action="">
                            <input type="hidden" name="articleId" value=${article.id}>
                            <input type="submit" name="submit" value=${submitValue}>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <script>
        $(function () {
            $(".datatable form").submit(function () {
                let articleId = $(this).find("input[name='articleId']").val();
                let $submit = $(this).find("input[name='submit']");
                ajax({
                    data: {
                        action: "setHiddenValue",
                        articleId: articleId,
                        hiddenValue: $submit.val() === "Hide"
                    },
                });
                $submit.val($submit.val() === "Show" ? "Hide" : "Show");

                return false;
            });
        })
    </script>
</@c.page>