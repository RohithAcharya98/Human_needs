const sessionId = Date.now().toString(); // Random session ID

function toggleChatbot() {
  const chatWindow = document.getElementById("chat-window");
  chatWindow.style.display = chatWindow.style.display === "flex" ? "none" : "flex";
}

function handleKey(event) {
  if (event.key === "Enter") sendMessage();
}

function appendMessage(text, sender) {
  const chatMessages = document.getElementById("chat-messages");
  const msg = document.createElement("div");
  msg.classList.add("chat-message", sender);
  msg.innerText = text;
  chatMessages.appendChild(msg);
  chatMessages.scrollTop = chatMessages.scrollHeight;
}

async function sendMessage() {
  const input = document.getElementById("chat-input");
  const message = input.value.trim();
  if (!message) return;

  appendMessage(message, "user");
  input.value = "";

  try {
    const response = await fetch("/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ message, sessionId })
    });

    const reply = await response.text();
    appendMessage(reply, "bot");
  } catch (err) {
    appendMessage("Oops, something went wrong!", "bot");
  }
}
