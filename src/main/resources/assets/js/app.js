angular.module('library', [ 'library.services', 'library.filters', 'library.directives' ])

.config(function($routeProvider) {
	$routeProvider.when('/login', {
		controller : LoginCtrl,
		templateUrl : 'partials/login.html'
	
	}).when('/books', {
		controller : BookListCtrl,
		templateUrl : 'partials/book-list.html'
	
	}).when('/books/new', {
		controller : BookDetailCtrl,
		templateUrl : 'partials/book-detail.html'

	}).when('/books/:bookId', {
		controller : BookDetailCtrl,
		templateUrl : 'partials/book-detail.html'

	}).otherwise({
		redirectTo : '/books'
	});

})

.config(function($httpProvider) {
	function errorInterceptor($q, $log, $location) {
		function success(response) {
			return response;
		}
		function error(response) {
			if (_.contains([404, 415, 500], response.status)) {
				$('#error-dialog').modal().on('hidden', function() {
					window.location = '.';
				});
			}
			return $q.reject(response);
		}
		return function(promise) {
			return promise.then(success, error);
		};
	}
	$httpProvider.responseInterceptors.push(errorInterceptor);

})

.run(function($rootScope, $location, userService) {
	/*
	$rootScope.$on('$routeChangeStart', function(event, next, current) {
		if (next.templateUrl !== "partials/login.html" && !userService.isLoggedIn()) {
			$location.path('/login');
		}
	});
	*/
});

