'use strict';

describe('Services', function() {

  describe('loginService', function() {

    beforeEach(module('library.services'));

    it('should not be logged in', inject(function(loginService) {
      expect(loginService.isLoggedIn()).toBe(false);

    }));

    it('should be logged in', inject(function(loginService) {
      loginService.login({ name : 'Me' });
      expect(loginService.isLoggedIn()).toBe(true);
      expect(loginService.getFullName()).toBe('Me');

    }));
    
    it('should be logged out', inject(function(loginService) {
      loginService.login({ name : 'Me' });
      expect(loginService.isLoggedIn()).toBe(true);
      loginService.logout();
      expect(loginService.isLoggedIn()).toBe(false);
    }));
    
    it('should be admin', inject(function(loginService) {
      loginService.login({ name : 'Me', role : 'ADMINISTRATOR' });
      expect(loginService.isAdmin()).toBe(true);
    }));
    
    it('should not be admin', inject(function(loginService) {
      loginService.login({ name : 'Me', role : 'USER' });
      expect(loginService.isAdmin()).toBe(false);
    }));
    
  });

});
