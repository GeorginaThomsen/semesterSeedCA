'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }])

        .controller('View4Ctrl', function ($http, $scope) {
            $scope.users = [];
            $scope.userNameD = '';
            $scope.item = 0;
            $http.get('api/admin/users')
                    .success(function (data, status, headers, config) {
                        $scope.users = data;
                    })
                    .error(function (data, status, headers, config) {

                    });

            $scope.deleteUser = function (index) {

                var preparedUrl = 'api/admin/user/' + $scope.users[index].name;
                $http({
                    method: 'DELETE',
                    url: preparedUrl
                }).then(function successCallback(response) {
                    $scope.users = response.data;
                    //$scope.users.splice(index, 1);
                }, function errorCallback(response) {

                });
            };

        });