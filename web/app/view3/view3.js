'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {

                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            $http({
                method: 'GET',
                url: 'api/currency/dailyRates',
                cache: true//Ask Aleks if he has used this
            }).then(function (response) {//Should/can there be a scope here?

                $scope.data = response.data;
//                alert($scope.data);
                
                
            }, function (response) {
                
            })
            
//                Or:
//                .controller('View3Ctrl', function ($http, $scope) {
//                $http({
//                method: 'GET',
//                        url: 'api/currency/dailyRates',
//                        cache: true
//                }).success(function (response) {
//                $scope.data = response.data;
//                }).error(function (response, status) {
//                    $scope.error = {message: response, status: status};
//                    console.log($scope.data.response.status)
//                })
            //---------------------------------------------------------------------------               

  
              
            
         
        });