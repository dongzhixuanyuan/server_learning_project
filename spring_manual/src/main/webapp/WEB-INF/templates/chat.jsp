<%--
  Created by IntelliJ IDEA.
  User: liudong
  Date: 2020/11/22
  Time: 11:36 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室</title>
</head>
<body>

<div id="chatTemplate" style="display:none">
    <div class="mb-1">
        <p>
            <span class="chat-name font-weight-bold"></span>:
            <br>
            <span class="chat-message"></span>
            <br>
            <span class="chat-timestamp text-muted" style="font-size:0.8em"></span>
        </p>
    </div>
</div>

<script>
    function sendChatMessage() {
        var input = $('#chatForm input[type=text]');
        var s = input.val();
        s = $.trim(s);
        if (s) {
            console.log('send: ' + s);
            window.chatWs.send(JSON.stringify({text: s}));
            input.val('');
        }
        return false;
    }

    function appendMessage(msg) {
        var templ = $('#chatTemplate').html();
        var div = $('<div></div>');
        div.html(templ);
        div.find('.chat-name').text(msg.name);
        div.find('.chat-timestamp').text(new Date(msg.timestamp).toLocaleString());
        div.find('.chat-message').text(msg.text);
        var room = $('#chatRoom');
        room.append(div);
        room.scrollTop(room.prop('scrollHeight') - room.height());
    }

    function openWS () {
        var ws = new WebSocket('ws://' + location.host + '/chat');
        ws.addEventListener('open', function (event) {
            $('#connecting').hide();
            $('#chatForm button[type=submit]').removeAttr('disabled');
        });
        ws.addEventListener('message', function (event) {
            console.log('message: ' + event.data);
            var msgs = JSON.parse(event.data);
            for (msg of msgs) {
                appendMessage(msg);
            }
        });
        ws.addEventListener('close', function () {
            $('#chatForm button[type=submit]').attr('disabled', 'disabled');
        });
        window.chatWs = ws;
    }
    openWS()
</script>

<h3>Chat Room</h3>

<div id="chatRoom" class="overflow-auto border border-info rounded p-3" style="height:480px">
    <div id="connecting">
        <div class="spinner-border" role="status">
            <span class="sr-only">Loading...</span>
        </div>
        <span>Connecting...</span>
    </div>
</div>

<form id="chatForm" onsubmit="return sendChatMessage()">
    <div class="form-group">
        <label>Message:</label>
        <input type="text" class="form-control" max-length="100">
    </div>
    <button type="submit" class="btn btn-primary" disabled>Submit</button>
</form>

</body>
</html>
