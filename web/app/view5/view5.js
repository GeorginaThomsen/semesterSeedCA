'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'View5Ctrl'
                });
            }])

        .controller('View5Ctrl', function ($http, $scope) {

            $scope.newuser = {};
            $scope.newuser.username = "";
            $scope.newuser.password = "";

            $scope.isSignedUp = false;

            $scope.signUp = function () {

                $scope.newuser = {
                    username: $scope.newuser.username,
                    password: $scope.newuser.password
                };
                var jsonUser = JSON.stringify($scope.newuser);
                $http.post('api/signUp', jsonUser)
                        .success(function (data, status, headers, config) {
                            $scope.isSignedUp = true;
                            $scope.data = data;                            
                        })
                        .error(function (data, status, headers, config) {
                            alert("error");
                        //$scope.isSignedUp = false;
                        });
                $scope.newuser.username = "";
                $scope.newuser.password = "";
                $scope.isSignedUp = false;
            };
        });