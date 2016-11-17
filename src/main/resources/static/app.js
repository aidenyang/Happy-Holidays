'use strict';

angular.module('HappyHolidaysApp', [
  'ngRoute',
  'HappyHolidaysApp.form',
  'HappyHolidaysApp.history',
  'HappyHolidaysApp.nav',
  'HappyHolidaysApp.404'
]).
config(['$locationProvider', '$routeProvider', '$httpProvider', function($locationProvider, $routeProvider, $httpProvider) {
  $locationProvider.hashPrefix('!');
  $routeProvider.when('/', {redirectTo:'/form'});
  $routeProvider.otherwise({redirectTo: '/404'});
  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);	