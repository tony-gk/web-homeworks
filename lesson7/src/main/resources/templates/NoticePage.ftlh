<#import "commons.ftlh" as c>

<@c.page>
    <form method="post" action="">
        <input type="hidden" name="action" value="/notice"/>
        <label>
            <textarea rows="10" cols="50" style="resize:none" name="content"></textarea>
        </label>
        <@c.error "noticeForm.content"/>
        <div><input type="submit" value="Add"></div>
    </form>
</@c.page>