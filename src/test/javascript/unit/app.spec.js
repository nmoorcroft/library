'use strict';

describe("Library App Tests", function() {
  
  beforeEach(module('libraryApp'));
  
  it('should logout', inject(function($rootScope, loginService, $location) {
    
    spyOn(loginService, 'logout');
    spyOn(loginService, 'clearHeaders');
    spyOn($location, 'path');
    
    expect($rootScope.logout).toBeDefined();
    
    $rootScope.logout();
    
    expect(loginService.logout).toHaveBeenCalled();
    expect(loginService.clearHeaders).toHaveBeenCalled();
    expect($location.path).toHaveBeenCalledWith('#/books');
    
  }));
  
  
});

