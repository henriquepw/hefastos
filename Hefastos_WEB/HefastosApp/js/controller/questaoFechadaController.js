angulaAppModulo.controller('questaoFechadaController', function (QuestaoFechadaService, $scope, $state) {

    $scope.questoesFechadas = [];

    $scope.criarQuestaoFechada = function () {        

        QuestaoFechadaService.criarQuestaoFechada($scope.questaoFechada)

            .then(function (response) {
                console.log(response.data);
                
                var questaoFechada = response.data;

                $scope.questoesFechadas.push(questaoFechada);                
            });
    };
    
});
