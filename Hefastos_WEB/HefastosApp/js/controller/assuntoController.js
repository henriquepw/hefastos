angulaAppModulo.controller('assuntoController', function (AssuntoService, $scope, $state, $mdToast) {

    $scope.assuntos = [];
    $scope.disciplinas = [];

    $scope.criarAssuntos = function () {        

        alert($scope.assunto);
        AssuntoService.criarAssunto($scope.assunto)

            .then(function (response) {
                console.log(response.data);
                
                $scope.assunto = response.data;

                $scope.assuntos.push($scope.assunto);    
                
                var toast = $mdToast.simple()
                    .textContent('Assunto cadastrado com sucesso.')
                    .position('top right')
                    .action('Ok')
                    .hideDelay(6000);
                $mdToast.show(toast);
            })
            .catch(function (data) {
                // Handle error here
                var toast = $mdToast.simple()
                    .textContent('Problema no cadastro do assunto.')
                    .position('top right')
                    .action('Ok')
                    .hideDelay(6000);
                $mdToast.show(toast);
            });
        };  
    
    $scope.listarAssuntos = function () { 
        
        AssuntoService.listarAssuntos()

            .then(function (response) {
                console.log(response.data);

                $scope.assuntos = response.data;
            });
    };
    
    $scope.deletarAssunto = function () { 
        
         AssuntoService.deletarAssunto($scope.assunto)

            .then(function (response) {
                console.log(response.data);
                
                $scope.assunto = response.data;                
            });
    };
    
    $scope.listarDisciplinas = function () {        

        AssuntoService.listarDisciplinas()

            .then(function (response) {
                console.log(response.data);
                
                $scope.disciplinas = response.data;
            
            });
        };
    
    
});