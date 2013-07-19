'use strict';

angular.module('library.directives', [])

.directive('navbar', function(authService) {
  return {
    replace : true,
    templateUrl : 'partials/navbar.html',
    link : function(scope, element, attrs) {
      if (authService.isLoggedIn()) {
        scope.fullName = authService.getFullName();
        scope.isAdmin = authService.isAdmin();
      }
    }
  };

});
