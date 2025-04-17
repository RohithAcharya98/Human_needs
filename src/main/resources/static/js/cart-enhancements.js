
function showToast(message) {
    const toast = document.getElementById('toast');
    const msg = document.getElementById('toast-msg');
    msg.textContent = message;
    toast.classList.remove('hidden');
    setTimeout(() => toast.classList.add('hidden'), 5000);
}

function undoAction() {
    showToast("Undo action triggered!");
    // Optional: Add undo backend request here
}

function fadeOutAndRemove(el) {
    el.classList.add('fade-out');
    setTimeout(() => el.remove(), 400);
}

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".ripple").forEach(btn => {
        btn.addEventListener("click", function (e) {
            const ripple = document.createElement("span");
            ripple.classList.add("ripple");
            ripple.style.left = `${e.offsetX}px`;
            ripple.style.top = `${e.offsetY}px`;
            this.appendChild(ripple);
            setTimeout(() => ripple.remove(), 600);
        });
    });
});
