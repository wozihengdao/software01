document.getElementById("send-button").addEventListener("click", function () {
  var userInput = document.getElementById("user-input").value;
  if (userInput.trim() !== "") {
    sendMessage(userInput);
    document.getElementById("user-input").value = ''
  }
});


function listVoices() {
  const voices = speechSynthesis.getVoices();
  voices.forEach((voice, index) => {
    console.log(index + ": " + voice.name + " (" + voice.lang + ")");
  });
}
speechSynthesis.onvoiceschanged = listVoices;


function sendMessage(message) {
  var chatContainer = document.getElementById("chat-container");
  var userMessageDiv = document.createElement("div");
  userMessageDiv.classList.add("user-message");
  userMessageDiv.innerHTML = `
    <div class="message-content">${message}</div>
  `;
  // <div class="avatar">
  //   <img src="../static/images/avatar/1.png" class="avatar"  alt="User Avatar">
  // </div>
  chatContainer.appendChild(userMessageDiv);

  var botMessageDiv = document.createElement("div");
  botMessageDiv.classList.add("bot-message");
  chatContainer.appendChild(botMessageDiv);

  // 准备机器人的思考状态
  var dots = '';
  var dotInterval = setInterval(function () {
    if (dots.length < 6) {
      dots += '.';
    } else {
      dots = '.';
    }
    botMessageDiv.innerHTML = `
      <div class="avatar-container">
        <img src="./02.png" class="avatar" alt="Kimi Avatar" style="width: 50px;height: 50px;">
        <span class="username">智慧巡检助手</span>
      </div>
      <div class="message-content" id="bot-mes">正在思考中${dots}</div>
    `;
  }, 500);

  // 发送消息到API
  var apiUrl = 'https://api.moonshot.cn/v1/chat/completions';
  var requestData = {
    model: "moonshot-v1-8k",
    messages: [
      {
        role: "system", content: `
      你是DeepSeek与 巡洋检修系统结合的大模型，，你是用户的小助手，为他解决问题。
      你解决用户的问题时，要结合《本草纲目》和中医知识，去解决用户的问题，最后不要用分点的形式给出方案。
      如果你认为用户的疾病不是特别严重，需要你根据《本草纲目》和中医的科学知识，给出建议（不要分条）。
      你需要并自行检索《本草纲目》科学的回答用户针对这本书提出的一些问题。
      你需要用科学的语言解决用户的问题，你回答的句数不要超过6句。` },
      { role: "user", content: message }
    ],
    temperature: 0.3
  };

  fetch(apiUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'sk-VtITtLb4695UfulYd265iOrLtW823gZ5zc4dab0ZD5O11Nsy'
    },
    body: JSON.stringify(requestData)
  })
    .then(response => response.json())
    .then(data => {
      clearInterval(dotInterval); // 停止生成省略号
      var kimiResponse = data.choices[0].message.content;
      var index = 0;
      botMessageDiv.innerHTML = `
      <div class="avatar-container">
        <img src="./02.png" class="avatar" alt="Kimi Avatar"  style="width: 50px;height: 50px;">
        <span class="username">智慧巡检助手</span>
      </div>
    `;
      var typingInterval = setInterval(function () {
        if (index < kimiResponse.length) {
          botMessageDiv.innerHTML += kimiResponse.charAt(index);
          index++;
        } else {
          clearInterval(typingInterval);
        }
      }, 50);
    })
    .catch(error => {
      console.error('发生错误:', error);
    });
}

// 暴露 sendMessage 函数
window.sendMessage = sendMessage;