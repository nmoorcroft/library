'use strict';

angular.module('library.controllers')

.controller('signUpCtrl', function($scope) {
  $scope.signup = function(user) {
    console.log('signup '+user);
    if (user.password != user.confirm) {
      $scope.error = 'Passwords must match';
    } else {
      $scope.error = null;
    }
  }
  $('#input-fullname').focus();

});

