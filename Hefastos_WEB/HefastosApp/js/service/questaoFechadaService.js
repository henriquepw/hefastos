angulaAppModulo.factory("QuestaoFechadaService", function ($http) {

    var urlBase = "http://34.209.165.216:8080/RestEasyApp";

    var _criarQuestaoFechada = function (questaoFechada) {
		return $http.post(urlBase + "/questaoFechada/inserir", questaoFechada);
	};
    
    var _removerQuestaoFechada = function (questaoFechada){
        return $http.post(urlBase + "/questaoFechada/deletar", questaoFechada);
    }

    return {
        criarQuestaoFechada: _criarQuestaoFechada,
        removerQuestaoFechada: _removerQuestaoFechada
    };

});