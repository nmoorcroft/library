'use strict';

angular.module('libraryApp', [ 'library.services', 'library.controllers', 'library.filters', 'library.directives' ])

.config(function($routeProvider) {
  $routeProvider.when('/login', {
    controller : 'loginCtrl',
    templateUrl : 'partials/login.html'

  }).when('/books', {
    controller : 'bookListCtrl',
    templateUrl : 'partials/book-list.html'

  }).when('/books/new', {
    controller : 'bookDetailCtrl',
    templateUrl : 'partials/book-detail.html'

  }).when('/books/:bookId', {
    controller : 'bookDetailCtrl',
    templateUrl : 'partials/book-detail.html'

  }).when('/signup', {
    controller : 'signUpCtrl',
    templateUrl : 'partials/signup.html'
      
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
      if (_.contains([ 404, 415, 500 ], response.status)) {
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

});


