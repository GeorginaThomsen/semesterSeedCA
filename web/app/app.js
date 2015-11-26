'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ngAnimate',
    'ui.bootstrap',
    'myApp.security',
    'myApp.view0',
    'myApp.view1',
    'myApp.view2',
    'myApp.view3',
    'myApp.view4',
    'myApp.view5',
    'myApp.view6',
    'myApp.filters',
    'myApp.directives',
    'myApp.factories',
    'myApp.services'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/view0'});
            }]).
        config(function ($httpProvider) {
            $httpProvider.interceptors.push('authInterceptor');
        });


