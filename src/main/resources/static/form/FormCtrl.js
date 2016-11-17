'use strict';

angular.module('HappyHolidaysApp.form', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/form', {
    templateUrl: 'form/form.html',
    controller: 'FormCtrl'
  });
}])

.controller('FormCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.newForm = function(senderName) {
		var emptyForm = {};
		emptyForm.senderName = senderName;
		return emptyForm;
	}
	$scope.sendAnother = function() {
		$scope.form.$setPristine();
		$scope.form = angular.copy($scope.newForm($scope.form.senderName));
	}
	$scope.submit = function() {
		// Validation
		$scope.form.sentDate = new Date().getTime();
		$scope.invalid = {};
		$scope.cannotSend = false;
		
		// This is kind of ugly I know 
		if ($scope.form.recipientEmail == '' || $scope.form.recipientEmail == null) {
			$scope.invalid.recipientEmail = true;
			$scope.cannotSend = true;
		}
		if ($scope.form.recipientName == '' || $scope.form.recipientName == null) {
			$scope.invalid.recipientName = true;
			$scope.cannotSend = true;
		}
		if ($scope.form.senderName == '' || $scope.form.senderName == null) {
			$scope.invalid.senderName = true;
			$scope.cannotSend = true;
		}
		if ($scope.form.content == '' || $scope.form.senderName == null) {
			$scope.invalid.content = true;
			$scope.cannotSend = true;
		}
		if (!$scope.cannotSend) {
			$scope.send();
		}
	}
	$scope.send = function() {
		$http.post('/messages', $scope.form)
		.then(function(data) {
			console.log(data);
			if (data.status == 201) {
				$scope.success = true;
			}
			else {
				$scope.sendError = true;
			}
		})
	}
	
}]);