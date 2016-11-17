'use strict';

angular.module('HappyHolidaysApp.nav', [ 'ngRoute' ])

.controller(
		'NavCtrl',
		[ '$rootScope', '$scope', '$location', '$http',
				function($rootScope, $scope, $location, $http) {
					$scope.isActive = function(viewRoute) {
						return viewRoute === $location.path();
					};
					
		
					// Removing auth code unless I can finish this feature
					// var self = this
					//
					// var authenticate = function(credentials, callback) {
					//
					// var headers = credentials ? {
					// authorization : "Basic "
					// + btoa(credentials.username + ":"
					// + credentials.password)
					// } : {};
					//
					// $http.get('user', {
					// headers : headers
					// }).then(function(response) {
					// if (response.data.name) {
					// $rootScope.authenticated = true;
					// } else {
					// $rootScope.authenticated = false;
					// }
					// callback && callback();
					// }, function() {
					// $rootScope.authenticated = false;
					// callback && callback();
					// });
					//
					// }
					//
					// authenticate();
					// self.credentials = {};
					// self.login = function() {
					// authenticate(self.credentials, function() {
					// if ($rootScope.authenticated) {
					// $location.path("/");
					// self.error = false;
					//							} else {
					//								$location.path("/login");
					//								self.error = true;
					//							}
					//						});
					//					};
				} ]);