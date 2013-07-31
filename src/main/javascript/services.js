angular.module('library.services', [ 'ngResource' ])

.factory('bookService', function($resource) {
  return $resource('api/books/:bookId', {}, {});
})

.factory('userService', function($resource) {
  return $resource('api/users/:userId', {}, {});
});

