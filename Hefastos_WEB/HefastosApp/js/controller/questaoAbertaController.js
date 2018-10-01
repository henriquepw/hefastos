angulaAppModulo.controller('questaoAbertaController', function (QuestaoAbertaService, $scope, $state) {

    $scope.questoesAbertas = [];

    $scope.criarQuestaoAberta = function () {        

        QuestaoAbertaService.criarQuestaoAberta($scope.questaoAberta)

            .then(function (response) {
                console.log(response.data);
                
                $scope.questaoAberta = response.data;

                $scope.questoesAbertas.push($scope.uestaoAberta);   
                
                
            });
    };
    
      $scope.listarQuestoesAbertas = function () { 
        
        QuestaoAbertaService.listarQuestoesAbertas()

            .then(function (response) {
                console.log(response.data);

                $scope.questoesAbertas = response.data;
            });
    };

    $scope.listarAssuntos = function () {        

        QuestaoAbertaService.listarAssuntos()

            .then(function (response) {
                console.log(response.data);
            
                $scope.assuntos = response.data;
            
            });
        };
    
    $scope.deletarQuestaoAberta = function () { 
        
         QuestaoAbertaService.deletarQuestaoAberta($scope.questaoAberta)

            .then(function (response) {
                console.log(response.data);
                
                $scope.questaoAberta = response.data;                
            });
    };
});
