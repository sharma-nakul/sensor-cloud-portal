var cmpe281 = angular.module('cmpe281', [ 'ngRoute' ]);

cmpe281.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'Angular/projects/templates/default.html',
		controller : 'loginController'
	}).when('/login', {
		templateUrl : 'Angular/projects/templates/login.html',
		controller : 'loginController'
	}).when('/signup', {
		templateUrl : 'Angular/projects/templates/signup.html',
		controller : 'signupController'
	}).when('/home', {
		templateUrl : 'Angular/projects/templates/home.html',
		controller : 'homeController'
	}).when('/home/sensor', {
		templateUrl : 'Angular/projects/templates/sensor.html',
		controller : 'sensorController'
	}).otherwise({
		redirectTo : '/login'
	});
} ]);
