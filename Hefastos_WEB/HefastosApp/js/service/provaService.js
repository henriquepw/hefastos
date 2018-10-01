angulaAppModulo.factory("ProvaService", function ($http) {

    var urlBase = "http://34.209.165.216:8080/RestEasyApp";

    var _gerarProva = function (prova) {
		return $http.post(urlBase + "/prova/gerar", prova);
	};
    
    var _pesquisarProva = function (prova){
        return $http.post(urlBase + "/prova/pesquisar", prova);
    }
    
    var _removerProva = function (prova){
        return $http.post(urlBase + "/prova/deletar", prova);
    }

    return {
        gerarProva: _gerarProva,
        pesqusarProva: _pesquisarProva,
        removerProva: _removerProva
    };

});
