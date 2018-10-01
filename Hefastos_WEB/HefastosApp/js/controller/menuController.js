angulaAppModulo.controller('MenuController', function ($scope, $timeout, $mdSidenav) {
    
    $scope.close = function () {
      // Component lookup should always be available since we are not using `ng-if`
      $mdSidenav('left').close()
        .then(function () {
          $log.debug("close LEFT is done");
        });

    };
  });