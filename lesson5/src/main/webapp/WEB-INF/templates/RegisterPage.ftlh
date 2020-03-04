<#-- @ftlvariable name="email" type="java.lang.String" -->
<#-- @ftlvariable name="login" type="java.lang.String" -->
<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="passwordConfirmation" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<#macro input_field id name value password=false>
    <div class="field">
        <div class="name">
            <label for="${id}">
                <#nested>
            </label>
        </div>
        <div class="value">
            <input id="${id}" name="${name}" value="${value}" type="${password?then('password', 'text')}"/>
        </div>
    </div>
</#macro>

<@c.page>
    <div class="form-box">
        <div class="header">Register</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="register"/>

                <@input_field id="login" name="login" value=login!>Login</@input_field>
                <@input_field id="email" name="email" value=email!>Email</@input_field>
                <@input_field id="password" name="password" value=password! password=true>Password</@input_field>
                <@input_field id="password-confirmation" name="passwordConfirmation"
                    value=passwordConfirmation! password=true>Password confirmation</@input_field>

                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input type="submit" value="Login">
                </div>
            </form>
        </div>
    </div>
</@c.page>
