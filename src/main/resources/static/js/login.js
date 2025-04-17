document.addEventListener("DOMContentLoaded", function () {
        if (!document.body.style.backgroundImage) {
            document.body.style.background = "url('/images/bg-login.jpg') no-repeat center center fixed";
            document.body.style.backgroundSize = "cover";
        }
});
setTimeout(() => {
    const msg = document.getElementById('logout-msg');
    if (msg) msg.style.display = 'none';
}, 3000);