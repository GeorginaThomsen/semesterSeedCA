'use strict';

angular.module('myApp.view0', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view0', {
                    templateUrl: 'app/view0/view0.html',
                    controller: 'View0Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View0Ctrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService) {
            }]);