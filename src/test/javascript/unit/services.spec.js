'use strict';

describe('Services', function() {

  describe('userService', function() {

    beforeEach(module('library.services'));

    it('should not be logged in', inject(function(userService) {
      expect(userService.isLoggedIn()).toBe(false);

    }));

    it('should be logged in', inject(function(userService) {
      userService.login({ name : 'Me' });
      expect(userService.isLoggedIn()).toBe(true);
      expect(userService.getFullName()).toBe('Me');

    }));
    
    it('should be logged out', inject(function(userService) {
      userService.login({ name : 'Me' });
      expect(userService.isLoggedIn()).toBe(true);
      userService.logout();
      expect(userService.isLoggedIn()).toBe(false);
    }));
    
    it('should be admin', inject(function(userService) {
      userService.login({ name : 'Me', role : 'ADMINISTRATOR' });
      expect(userService.isAdmin()).toBe(true);
    }));
    
    it('should not be admin', inject(function(userService) {
      userService.login({ name : 'Me', role : 'USER' });
      expect(userService.isAdmin()).toBe(false);
    }));
    
  });

});
