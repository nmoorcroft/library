'use strict';

angular.module('library.services', [ 'ngResource' ])

.factory('bookService', function($resource) {
  return $resource('api/books/:bookId', {}, {});
})

.factory('userService', function($resource) {
  return $resource('api/users/:userId', {}, {});
})

.factory('loginService', function($http) {
  var currentUser = null;
  return {
    setHeaders : function(username, password) {
      $http.defaults.headers.common.Authorization = 'Basic ' + Base64.encode(username + ':' + password);
    },
    clearHeaders : function() {
      delete $http.defaults.headers.common.Authorization;
    },
    login : function(user) { currentUser = user; },
    isLoggedIn : function() { return currentUser !== null; },
    isAdmin : function() { return this.isLoggedIn() && currentUser.role == 'ADMINISTRATOR'; },
    getFullName : function() { return currentUser.name; },
    logout : function() { currentUser = null; }
  
  };

});
