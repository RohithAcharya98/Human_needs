// Show toast if errorToast element exists
const toastEl = document.getElementById('errorToast');
if (toastEl && window.bootstrap) {
    const toast = new bootstrap.Toast(toastEl);
    toast.show();
}

// Dark mode toggle logic
const toggle = document.getElementById('darkModeToggle');
const modeIcon = document.getElementById('modeIcon');
const modeLabel = document.getElementById('modeLabel');

toggle?.addEventListener('click', () => {
    const root = document.documentElement;
    const active = toggle.getAttribute('data-active') === 'true';
    toggle.setAttribute('data-active', !active);
    root.setAttribute('data-theme', active ? 'light' : 'dark');

    modeIcon.textContent = active ? '🌞' : '🌙';
    modeLabel.textContent = active ? 'Light Mode' : 'Dark Mode';
});

// Show/hide password and confirm password
document.getElementById('showPassword')?.addEventListener('change', function () {
    const passwordField = document.getElementById('password');
    const confirmPasswordField = document.getElementById('confirmPassword');

    const type = this.checked ? 'text' : 'password';
    if (passwordField && confirmPasswordField) {
        passwordField.type = type;
        confirmPasswordField.type = type;
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const sendOtpBtn = document.getElementById("sendOtpBtn");
    const verifyOtpBtn = document.getElementById("verifyOtpBtn");
    const otpField = document.getElementById("otpField");
    const verifyOtpBtnContainer = document.getElementById("verifyOtpBtnContainer");
    const submitBtn = document.getElementById("submitBtn");

    sendOtpBtn.addEventListener("click", async () => {
        const email = document.getElementById("email").value;

        if (!email) {
            alert("Please enter an email before requesting OTP.");
            return;
        }

        const response = await fetch('/send-otp', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ email })
        });

        if (response.ok) {
            otpField.classList.remove("hidden");
            verifyOtpBtnContainer.classList.remove("hidden");
            alert("OTP sent to your email.");
        } else {
            alert("Failed to send OTP. Try again.");
        }
    });

    verifyOtpBtn.addEventListener("click", async () => {
        const email = document.getElementById("email").value;
        const otp = document.getElementById("otpInput").value;

        const response = await fetch('/verify-otp', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ email, otp })
        });

        if (response.ok) {
            alert("OTP Verified. You can now Sign Up.");
            document.getElementById("submitBtn").classList.remove("hidden");
            sendOtpBtn.disabled = true;
            verifyOtpBtn.disabled = true;
        } else {
            alert("Invalid OTP. Please try again.");
        }
    });
});


