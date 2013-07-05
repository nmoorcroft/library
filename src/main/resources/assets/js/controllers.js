function LoginCtrl($scope, $http, $location, userService, loginService) {
	$scope.login = function(user) {
		loginService.setHeaders(user.username, user.password);
		$http.get("api/authenticate").success(function(data) {
			userService.currentUser = data;
			$location.path("/books");

		}).error(function(data) {
			$scope.error = "Invalid username or password.";
			user.password = undefined;
		});
	};

	$('#inputUsername').focus();

}

function BookListCtrl($scope, $location, bookService) {
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

	$scope.select = function(id) {
		$location.path("/books/" + id);
	};

}

function BookDetailCtrl($scope, $routeParams, $location, bookService) {
	var id = $routeParams.bookId;
	if (!_.isUndefined(id)) {
		$scope.book = bookService.get({
			bookId : id
		}, function() {
			if (!_.isUndefined($scope.book.artwork)) {
				$('#artworkImg').attr('src', 'api/artwork/'+$scope.book.artwork);
			}
		});
	}
	
	$scope.save = function(book) {
		bookService.save(book, function() {
			$location.path("/books");
		});
	};

	$scope.remove = function(id) {
		bookService.remove({
			bookId : id
		}, function() {
			$location.path("/books");
		});
	};

	$scope.cancel = function() {
		$location.path("/books");
	};

	$('#inputArtwork').bootstrapFileInput();
	$('#inputArtwork').fileupload({
		dataType : 'text',
		done : function(e, data) {
			$('#artworkImg').attr('src', 'api/artwork/'+data.result);
			$scope.book.artwork = data.result;
		}
	});
	
	$('#inputTitle').focus();

}
