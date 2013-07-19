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

function LoginCtrl($scope, $http, $location, loginService) {
  $scope.user = {};
  $scope.login = function(user) {
    loginService.setHeaders(user.username, user.password);
    $http.get('api/authenticate').success(function(data) {
      loginService.login(data);
      $location.path('/books');

    }).error(function(data) {
      $scope.error = 'Invalid username or password.';
      user.password = undefined;
      $('#input-username').focus();
    });
  };

  $('#input-username').focus();

}

function BookListCtrl($scope, $location, bookService, loginService) {
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

  $scope.canEdit = loginService.isAdmin();
  
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
