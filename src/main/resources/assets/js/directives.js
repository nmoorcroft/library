angular.module('library.directives', [])

.directive('navbar', function(userService) {
  return {
    replace : true,
    templateUrl : "partials/navbar.html",
    link : function(scope, element, attrs) {
      if (userService.isLoggedIn())
        scope.fullName = userService.currentUser.name;
      $(element).find('ul.nav li').each(function() {
        if ($(this).hasClass(attrs.selected)) {
          $(this).addClass('active');
        }
      });
    }
  };

});
