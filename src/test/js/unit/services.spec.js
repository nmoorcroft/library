describe('Services', function() {

	describe('userService', function() {
		
		beforeEach(module('library.services'));
		
		it('should not be logged in', inject(function(userService) {
			expect(userService.isLoggedIn()).toBe(false);
			
		}));

		it('should be logged in', inject(function(userService) {
			userService.currentUser = { name : 'Me' };
			expect(userService.isLoggedIn()).toBe(true);
			
		}));

	});
	
	
});

