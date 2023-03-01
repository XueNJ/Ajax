/*
// **********************************************************************************************************************************
// 套件名稱：bootstrap-slidemenu.js 
// 備 註：
// 相依程式：
// 相依樣式：
// ***********************************************************************************************************************************
*/

/// <summary>
/// Menu Left Bar script  && icon right or left
/// </summary>
window.addEventListener('DOMContentLoaded', event => {
    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle'); // Slide Bar 
    let arrowCollapse = document.querySelector('#logo-name__icon'); // icon
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
            arrowCollapse.classList.toggle('collapseBi');
            arrowCollapse.classList = arrowCollapse.classList.contains('collapseBi') ? 'bi bi-arrow-bar-right icon_white collapseBi' : 'bi bi-arrow-bar-left icon_white';
        });
    }

});