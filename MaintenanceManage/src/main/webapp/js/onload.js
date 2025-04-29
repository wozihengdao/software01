document.addEventListener('DOMContentLoaded', function () {
  // 这里放置您要执行的JS脚本
  console.log('DOMContentLoaded事件触发，脚本开始执行');
  var chatContainer = document.getElementById("chat-container");

  var botMessageDiv = document.createElement("div");
  botMessageDiv.classList.add("bot-message");
  chatContainer.appendChild(botMessageDiv);

  botMessageDiv.innerHTML = `
      <div class="avatar-container">
        <img src="./02.png" class="avatar" alt="Kimi Avatar" style="width: 50px; height: 50px;">
        <span class="username">智慧巡检助手</span>
      </div>
    `;
  var index = 0;
  kimiResponse = `我是智慧巡检助手，很高兴为您服务:`
  var typingInterval = setInterval(function () {
    if (index < kimiResponse.length) {
      botMessageDiv.innerHTML += kimiResponse.charAt(index);
      index++;
    } else {
      clearInterval(typingInterval);
    }
  }, 50);

});