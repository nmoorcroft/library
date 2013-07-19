'use strict';

describe("Library App Tests", function() {
  
  beforeEach(module('libraryApp'));
  
  it('should logout', inject(function($rootScope, authService, $location) {
    
    spyOn(authService, 'logout');
    spyOn($location, 'path');
    
    expect($rootScope.logout).toBeDefined();
    
    $rootScope.logout();
    
    expect(authService.logout).toHaveBeenCalled();
    expect($location.path).toHaveBeenCalledWith('#/books');
    
  }));
  
  
});

