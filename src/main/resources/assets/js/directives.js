'use strict';

angular.module('library.directives', [])

.directive('navbar', function(loginService) {
  return {
    replace : true,
    templateUrl : 'partials/navbar.html',
    link : function(scope, element, attrs) {
      if (loginService.isLoggedIn()) {
        scope.fullName = loginService.getFullName();
        scope.isAdmin = loginService.isAdmin();
      }
    }
  };

});
