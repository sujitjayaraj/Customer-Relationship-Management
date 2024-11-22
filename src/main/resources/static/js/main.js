"use strict";

var treeviewMenu = document.querySelector('.app-menu');

// Toggle Sidebar
document.querySelectorAll('[data-toggle="sidebar"]').forEach(function (element) {
    element.addEventListener('click', function (event) {
        document.querySelector('.app').classList.toggle('sidenav-toggled');
    });
});

// Activate sidebar treeview toggle
document.querySelectorAll('[data-toggle="treeview"]').forEach(function (element) {
    const parent = this.parentElement;

    if (!parent.classList.contains('is-expanded')) {
        treeviewMenu.querySelectorAll('[data-toggle="treeview"]').forEach(function (item) {
            item.parentElement.classList.remove('is-expanded');
        });
    }

    parent.classList.toggle('is-expanded');
});

document.querySelectorAll('[data-bs-toggle="dropdown"]').forEach(function (element){
    new bootstrap.Dropdown(element);
});

document.querySelectorAll('[data-toggle="treeview"].is-expanded').forEach(function (element) {
    element.parentElement.classList.toggle('is-expanded');
})