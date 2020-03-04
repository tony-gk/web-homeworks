<#-- @ftlvariable name="login" type="java.lang.String" -->
<#-- @ftlvariable name="email" type="java.lang.String" -->
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
    <div class="registration form-box">
        <div class="header">Register</div>
        <div class="body">
            <form method="post" action="">
                <@input_field id="login" name="login" value=login!>Login</@input_field>
                <@input_field id="email" name="email" value=email!>Email</@input_field>
                <@input_field id="password" name="password" value=password! password=true>Password</@input_field>
                <@input_field id="passwordConfirmation" name="passwordConfirmation"
                value=passwordConfirmation! password=true>Password confirmation</@input_field>

                <div class="error">
                    ${error!}
                </div>
                <div class="button-field">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".registration form").submit(function () {
                const login = $(this).find("input[name='login']").val();
                const email = $(this).find("input[name='email']").val();
                const password = $(this).find("input[name='password']").val();
                const passwordConfirmation = $(this).find("input[name='passwordConfirmation']").val();
                const $error = $(this).find(".error");
                ajax({
                    errorHandle: true,
                    $error : $error,
                    data: {
                        action: "register",
                        login: login,
                        email: email,
                        password: password,
                        passwordConfirmation: passwordConfirmation,
                    },
                });

                return false;
            });
        })
    </script>
</@c.page>
