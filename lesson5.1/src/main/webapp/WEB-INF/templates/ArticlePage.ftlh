<#import "commons.ftlh" as c>

<@c.page>
    <div class="article-form">
        <form method="post" action="">
            <div class="title">
                <label for="title">
                    Title:
                </label>
                <input id="title" name="title">
            </div>
            <textarea placeholder="Input your text here" name="text"></textarea>

            <div><input type="submit" value="Create"></div>

        </form>
    </div>
</@c.page>

<script>
    $(function () {
        $(".article-form form").submit(function () {
            const $context = $(this);
            const title = $(this).find("input[name='title']").val();
            const text = $(this).find("textarea[name='text']").val();

            ajax({
                data: {
                    action: "create",
                    title: title,
                    text: text
                },
                success: function (response) {
                    if (response["error"]) {
                        $context.find("input[value='Create']").notify(response["error"], {position: "top"});
                    } else {
                        location.href = "/article";
                    }
                }
            });
            return false;
        })
    })
</script>