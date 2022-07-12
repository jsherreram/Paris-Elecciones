(function () {
    angular.module("app", ['ngRoute', 'geolocation', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert', 'fxpicklist', 'angularUtils.directives.dirPagination']);
    angular.module('app.services', ['ngResource']);
})();