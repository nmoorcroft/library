angular.module('library.controllers')

.controller('loginCtrl', function($scope, $http, $location, authService) {
  $scope.login = function(user) {
    $http.get('api/authenticate', {
      headers: { 
        'Authorization' : authService.createAuthHeader(user.username, user.password) 
      }

    }).success(function(data) {
      authService.login(data, user.password);
      $location.path('/books');

    }).error(function(data) {
      $scope.error = 'Invalid username or password.';
      user.password = undefined;
      $('#input-password').focus();
      
    });
    
  };

  $('#input-username').focus();

});


