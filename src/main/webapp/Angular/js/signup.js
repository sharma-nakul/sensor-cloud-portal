/**
 * Created by Vaishampayan Reddy on 4/9/2016.
 */
cmpe281.controller('signupController', function($scope, $routeParams, $http) {

	$scope.signup = new function() {
		var registrationData = {};
		registrationData.first_name = $scope.first_name;
		registrationData.last_name = $scope.last_name;
		registrationData.email = $scope.email;
		registrationData.passowrd = $scope.password;
		$http({
			method: 'POST',
			url: '/api/register',
			data: registrationdata,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data){
			if(data.status === 200) {
				alert(data);
			}
			else if(data.status === 403) {
				alert(data);
			}
		});	
	}
});