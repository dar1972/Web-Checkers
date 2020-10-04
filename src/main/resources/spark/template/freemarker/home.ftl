<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />
  
  <div class="body">

    
    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <h2> Players Online </h2>
    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->
    <#if currentUser??>
      
      <#list userList?keys as key>

        <a> ${key} </a>

        <form action="./" method="POST">
        Write an Opponent:
        <br/>
        <input name="opponentName" />
        <br/><br/>
        <button type="submit">Ok</button>
      </form>
        <!--<button id="${key}" onclick = "on()" action = "./" method = "POST" type="submit" name = "opponentName">${key}</button>-->

      </#list>
    <#else>
      <#if lobbySize != 0>
          There is/are ${lobbySize} players available.
      <#else>
          There are no other players available to play at this time.
      </#if>
    </#if>

  </div>

</div>
</body>

</html>
