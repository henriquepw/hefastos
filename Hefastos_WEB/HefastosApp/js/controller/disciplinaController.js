angulaAppModulo.controller('disciplinaController', function (DisciplinaService, $scope, $state, $mdToast) {

    $scope.disciplinas = [];

    $scope.criarDisciplina = function () {        

        DisciplinaService.criarDisciplinas($scope.disciplina)

            .then(function (response) {
                console.log(response.data);
                
                $scope.disciplina = response.data;

                $scope.disciplinas.push($scope.disciplina);  
            
                var toast = $mdToast.simple()
                    .textContent('Disciplina cadastrada com sucesso.')
                    .position('top right')
                    .action('Ok')
                    .hideDelay(6000);
                $mdToast.show(toast);
            })
            .catch(function (data) {
                // Handle error here
                var toast = $mdToast.simple()
                    .textContent('Problema no cadastro da disciplina.')
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
    
    $scope.deletarDisciplina = function () { 
        
         DisciplinaService.deletarDisciplina($scope.disciplina)

            .then(function (response) {
                console.log(response.data);
                
                $scope.disciplina = response.data;                
            });
    }
    
});
