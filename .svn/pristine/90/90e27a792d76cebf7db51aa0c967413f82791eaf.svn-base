'use strict';

var services = angular.module('app.global.services', ['ngResource', 'ngCookies']);

services.factory('httpInterceptor', function ($q, $cookies) {
    return {
        'request': function (config) {
            loadingStart();
            config.headers['Accept'] = 'application/json';
            config.headers['Pragma'] = 'no-cache';
            config.headers['Cache-Control'] = 'no-cache, no-store, must-revalidate';
            config.headers['Expires'] = '0';
            config.headers[formulario.global.headerTokenKey] = $cookies[formulario.global.cookieTokenKey];
            return config;
        },
        'requestError': function (rejection) {
            return rejection;
        },
        'response': function (response) {
            loadingComplete();
            return response;
        },
        'responseError': function (rejection) {
            if (rejection.status == formulario.global.authErrorStatus){
                alert("No se encuentra autenticado, es necesario autenticarse.");
                location.href = formulario.global.logoutAction;
            }
            else {
                alert("Se ha presentado un error, por favor intente de nuevo.");
                loadingComplete();
            }
            return $q.reject(rejection);
        }
    };
});

