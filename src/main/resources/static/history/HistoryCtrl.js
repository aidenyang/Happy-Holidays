'use strict';

angular.module('HappyHolidaysApp.history', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/history-view', {
    templateUrl: 'history/history.html',
    controller: 'HistoryCtrl'
  });
}])

.controller('HistoryCtrl', ['$scope', '$http', function($scope, $http) {
	$http.get('/messages').then(function(res) {
		$scope.messages = res.data;
		console.log($scope.messages);
		if ($scope.messages.length == 0) {
			$scope.noMessages = true;
		}
	});
}]);