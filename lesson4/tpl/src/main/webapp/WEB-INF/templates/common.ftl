<#macro header>
    <header>
        <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
        <div class="languages">
            <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
            <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user=user nameOnly=true/>
                |
                <a href="#">Logout</a>
            <#else>
                <a href="#">Enter</a>
                |
                <a href="#">Register</a>
            </#if>
        </div>
        <nav>
            <#assign menu = [ {"uri": "/index", "name": "Index"},
                              {"uri": "/misc/help", "name": "Help"},
                              {"uri": "/users", "name": "Users"}]/>
            <ul>
                <#list menu as item>
                    <#assign class=""/>
                    <#if current_uri==item.uri>
                        <#assign class="current"/>
                    </#if>
                    <li>
                        <a href="${item.uri}" class="${class}">${item.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as post>
            <section>
                <div class="header">
                    Post ${post.id}
                </div>
                <div class="body">
                    ${cropText(post.text)}
                </div>
                <div class="footer">
                    <a href="/post?id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        <a href="#">Codeforces</a> &copy; 2010-2019 by Mike Mirzayanov
    </footer>
</#macro>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" type="text/css" href="/css/normalize.css">
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="icon" href="/favicon.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#function cropText text>
    <#if (text?length > 250)>
        <#return text[0..250] + "…">
    <#else>
        <#return text>
    </#if>
</#function>


<#macro displayPost postId cropText>
    <#assign post=findBy(posts, "id", postId?number)!/>
    <#if post?has_content>
        <#assign user=c.findBy(users, "id", post.user_id)!/>
        <#assign postText=post.text>
        <#if (postText?length > 250) && cropText == true>
            <#assign postText=postText[0..250] + "…"/>
        </#if>
        <#assign postText="<p>" + postText?replace("(\n)+", "</p><p>", 'r') + "</p>"/>
        <article>
            <div class="title">${post.title}</div>
            <div class="information">By ${user.handle!}</div>
            <div class="body">${postText}</div>
            <ul class="attachment">
                <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
            </ul>
            <div class="footer">
                <div class="left">
                    <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    2 days ago
                    <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">68</a>
                </div>
            </div>
        </article>
    <#else>
        <h1>No such post</h1>
    </#if>
</#macro>

<#macro userlink user nameOnly>
    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#else>
        <a href="/user?handle=${user.handle}" class="userlink" style="color:${user.color?lower_case}">
            ${user.handle}
        </a>
    </#if>
</#macro>

<#function findBy items key val>
    <#list items as item>
        <#if item[key]==val>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function nextItem items key>
    <#list items as item>
        <#if item == key && item_has_next>
            <#return items[item_index + 1]>
        </#if>
    </#list>
</#function>

<#function prevItem items key>
    <#list items as item>
        <#if item == key && item_index != 0>
            <#return items[item_index - 1]>
        </#if>
    </#list>
</#function>
