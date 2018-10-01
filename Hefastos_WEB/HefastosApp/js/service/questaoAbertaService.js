angulaAppModulo.factory("QuestaoAbertaService", function ($http) {

    var urlBase = "http://34.209.165.216:8080/RestEasyApp";

    var _criarQuestaoAberta = function (questaoAberta) {
		return $http.post(urlBase + "/questaoAberta/inserir", questaoAberta);
	};
    
    var _deletarQuestaoAberta = function (questaoAberta){
        return $http.post(urlBase + "/questaoAberta/deletar", questaoAberta);
    };
    
    var _listarQuestoesAbertas = function (){
        return $http.get(urlBase + "/questao/aberta/listar");
    };
    
    var _listarAssuntos = function (){
        return $http.get(urlBase + "/assunto/listar");
    };

    return {
        criarQuestaoAberta: _criarQuestaoAberta,
        deletarQuestaoAberta: _deletarQuestaoAberta,
        listarQuestoesAbertas: _listarQuestoesAbertas,
        listarAssuntos: _listarAssuntos
    };

});
