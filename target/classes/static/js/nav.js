// Handle hover for desktop
const profileTrigger = document.getElementById('profileTrigger');
const profileDropdown = document.getElementById('profileDropdown');
const container = document.getElementById('profile-container');

container.addEventListener('mouseenter', () => {
    profileDropdown.classList.add('show');
    profileTrigger.setAttribute('aria-expanded', 'true');
});

container.addEventListener('mouseleave', () => {
    profileDropdown.classList.remove('show');
    profileTrigger.setAttribute('aria-expanded', 'false');
});

// Ensure Bootstrap dropdown works on click
profileTrigger.addEventListener('click', function(event) {
    const isOpen = profileDropdown.classList.contains('show');
    if (!isOpen) {
        profileDropdown.classList.add('show');
        profileTrigger.setAttribute('aria-expanded', 'true');
    } else {
        profileDropdown.classList.remove('show');
        profileTrigger.setAttribute('aria-expanded', 'false');
    }
});

// Close dropdown if clicked outside
document.addEventListener('click', (e) => {
    if (!container.contains(e.target)) {
        profileDropdown.classList.remove('show');
        profileTrigger.setAttribute('aria-expanded', 'false');
    }
});

