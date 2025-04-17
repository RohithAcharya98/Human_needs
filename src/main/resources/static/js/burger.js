const burgerTrigger = document.getElementById('burgerTrigger');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');
    const closeSidebar = document.getElementById('closeSidebar');

    burgerTrigger.addEventListener('click', () => {
        sidebar.style.transform = 'translateX(0)';
        overlay.style.display = 'block';
    });

    overlay.addEventListener('click', () => {
        sidebar.style.transform = 'translateX(-100%)';
        overlay.style.display = 'none';
    });

    closeSidebar.addEventListener('click', () => {
        sidebar.style.transform = 'translateX(-100%)';
        overlay.style.display = 'none';
    });