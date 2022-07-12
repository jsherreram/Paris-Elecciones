(function () {
    angular.module("app", ['ngRoute', 'ngAnimate', 'hSweetAlert','ui.bootstrap', 'app.global.services', 'app.services', 'angularUtils.directives.dirPagination']);
    angular.module('app.services', ['ngResource']);
})();