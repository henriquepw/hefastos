angulaAppModulo.controller('professorController', function (ProfessorService, $scope, $state) {

    $scope.professores = [];
    $scope.professor = {};

    $scope.cadastrarProfessor = function () {        

        ProfessorService.cadastrarProfessor($scope.professor)

            .then(function (response) {
                console.log(response.data);
                
                $scope.professor = response.data;

                $scope.professores.push($scope.professor);  

                $state.go('home');

            });
    };


    $scope.loginProfessor = function () {        

        ProfessorService.loginProfessor($scope.professor)

            .then(function (response) {
                console.log(response.data);

            });
    };
    
    $scope.goToIndex = function () {        

                $state.go('/');

    };
    
});

