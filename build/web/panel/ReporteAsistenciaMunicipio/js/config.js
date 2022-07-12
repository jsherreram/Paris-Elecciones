(function () {
    angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);
    angular.module('app.services', ['ngResource']);
})();