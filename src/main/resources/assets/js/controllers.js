'use strict';

function SignUpCtrl($scope) {
  $scope.signup = function(user) {
    console.log('signup '+user);
    if (user.password != user.confirm) {
      $scope.error = 'Passwords must match';
    } else {
      $scope.error = null;
    }
  }
  $('#input-fullname').focus();
}

function LoginCtrl($scope, $http, $location, authService) {
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

}

function BookListCtrl($scope, $location, bookService, authService) {
  $scope.books = bookService.query();
  $scope.query = '';

  $scope.search = function(query) {
    $scope.books = bookService.query({
      q : query
    }, function() {
      $scope.showClear = !_.isEmpty(query);
    });
  };

  $scope.$watch('query', function() {
    $scope.showClear = false;
  });

  $scope.searchIcon = function() {
    if ($scope.showClear) {
      $scope.query = '';
    }
    $scope.search($scope.query);
  };

  $scope.canEdit = authService.isAdmin();
  
}

function BookDetailCtrl($scope, $routeParams, $location, bookService) {
  var id = $routeParams.bookId;
  if (!_.isUndefined(id)) {
    $scope.book = bookService.get({
      bookId : id
    });
  }

  $scope.artworkUrl = function() {
    if (!_.isUndefined($scope.book)) {
      if (!_.isUndefined($scope.book.artwork)) {
        return 'api/artwork/' + $scope.book.artwork;
      }
    }
    return '';
  }

  $scope.save = function(book) {
    bookService.save(book, function() {
      $location.path('/books');
    });
  };

  $scope.remove = function(id) {
    bookService.remove({
      bookId : id
    }, function() {
      $location.path('/books');
    });
  };

  $('#input-artwork').bootstrapFileInput();
  $('#input-artwork').fileupload({
    dataType : 'text',
    done : function(e, data) {
      $scope.$apply(function() {
        $scope.book.artwork = data.result;
      });
    }
  });

  $('#input-title').focus();

}
