'use strict';

angular.module('HappyHolidaysApp.404', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/404', {
    templateUrl: '404/404.html'
  });
}]);