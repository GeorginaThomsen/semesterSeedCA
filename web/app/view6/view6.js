'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'app/view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])
        .controller('View6Ctrl', function ($scope, DataFromApi, InfoService) {

            $scope.InfoService = InfoService;

            if (InfoService.search === undefined) {
                InfoService.search = 'cphbusiness';
            }
            if (InfoService.country === undefined || InfoService.country === '') {
                InfoService.country = 'dk';
            }
            if (InfoService.option === undefined || InfoService.option === '') {
                InfoService.option = 'search';
            }

            DataFromApi.getDataFromApi(InfoService.option, InfoService.search, InfoService.country)
                    .success(function (response) {
                        $scope.details = response;
                    })
                    .error(function (response) {
                        console.log(response);
                        return response;
                    });
        });


  