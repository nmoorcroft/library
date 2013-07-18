'use strict';

describe("Library App Tests", function() {
  
  beforeEach(module('libraryApp'));
  
  it('should logout', inject(function($rootScope, userService, loginService, $location) {
    
    spyOn(userService, 'logout');
    spyOn(loginService, 'clearHeaders');
    spyOn($location, 'path');
    
    expect($rootScope.logout).toBeDefined();
    
    $rootScope.logout();
    
    expect(userService.logout).toHaveBeenCalled();
    expect(loginService.clearHeaders).toHaveBeenCalled();
    expect($location.path).toHaveBeenCalledWith('#/books');
    
  }));
  
  
});

