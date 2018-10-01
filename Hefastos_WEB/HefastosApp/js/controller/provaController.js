angulaAppModulo.controller('provaController', function (ProvaService, $scope, $state, $mdToast) {

    $scope.provas = [];

    $scope.gerarProva = function () {        

        ProvaService.gerarProva($scope.prova)

            .then(function (response) {
                console.log(response.data);
                
                $scope.prova = response.data;

                $scope.provas.push($scope.prova);      
            
                 var toast = $mdToast.simple()
                    .textContent('Questão cadastrada com sucesso.')
                    .position('top right')
                    .action('Ok')
                    .hideDelay(6000);
                $mdToast.show(toast);
            })
            .catch(function (data) {
                // Handle error here
                var toast = $mdToast.simple()
                    .textContent('Problema no cadastro da questão.')
                    .position('top right')
                    .action('Ok')
                    .hideDelay(6000);
                $mdToast.show(toast);
            });
    };
    
    $scope.listarDisciplinas = function () {        

        DisciplinaService.listarDisciplinas()

            .then(function (response) {
                console.log(response.data);
                
                $scope.disciplinas = response.data;
            
            });
        };
    
    $scope.listarAssuntos = function () {        

        AssuntoService.listarAssuntos()

            .then(function (response) {
                console.log(response.data);
            
                $scope.assuntos = response.data;
            
            });
        };
    
});
