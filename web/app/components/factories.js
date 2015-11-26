'use strict';

/* Place your global Factory-service in this file */

angular.module('myApp.factories', []).
        factory('InfoFactory', function () {
            var info = "Hello World from a Factory";
            var getInfo = function getInfo() {
                return info;
            };
            return {
                getInfo: getInfo
            };
        })
        .factory('DataFromApi', function ($http) {
            return{
                getDataFromApi: function (option, search, dk) {
                    return $http.get("http://cvrapi.dk/api?" + option + "=" + search + "&country=" + dk);

                }
            };
        });