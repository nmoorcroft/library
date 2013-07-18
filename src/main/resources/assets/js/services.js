'use strict';

angular.module('library.services', [ 'ngResource' ])

.factory('bookService', function($resource) {
  return $resource('api/books/:bookId', {}, {});
})

.factory('userService', function() {
  var currentUser = null;
  return {
    login : function(user) { currentUser = user; },
    isLoggedIn : function() { return currentUser !== null; },
    isAdmin : function() { return this.isLoggedIn() && currentUser.role == 'ADMINISTRATOR'; },
    getFullName : function() { return currentUser.name; },
    logout : function() { currentUser = null; }
  };
})

.factory('loginService', function($http) {
  return {
    setHeaders : function(username, password) {
      $http.defaults.headers.common.Authorization = 'Basic ' + Base64.encode(username + ':' + password);
    },
    clearHeaders : function() {
      delete $http.defaults.headers.common.Authorization;
    }
  
  };

});
