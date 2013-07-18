'use strict';

angular.module('library.directives', [])

.directive('navbar', function(userService) {
  return {
    replace : true,
    templateUrl : 'partials/navbar.html',
    link : function(scope, element, attrs) {
      if (userService.isLoggedIn()) {
        scope.fullName = userService.getFullName();
        scope.isAdmin = userService.isAdmin();
      }
    }
  };

});
