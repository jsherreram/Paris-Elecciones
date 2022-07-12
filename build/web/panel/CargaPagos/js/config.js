(function () {
    angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services', 'hSweetAlert', 'angularUtils.directives.dirPagination', 'ngFileSaver']);
    angular.module('app.services', ['ngResource']);
})();