angulaAppModulo.factory("AssuntoService", function ($http) {

    var urlBase = "http://34.209.165.216:8080/RestEasyApp";

    var _criarAssunto = function (assunto) {
		return $http.post(urlBase + "/assunto/inserir", assunto);
	};
    
    var _removerAssunto = function (assunto){
        return $http.post(urlBase + "/assunto/deletar", assunto);
    };
    
    var _listarAssuntos = function (){
        return $http.get(urlBase + "/assunto/listar/");
    };
    
    var _listarDisciplinas = function (disciplina){
        return $http.get(urlBase + "/disciplina/listar");
    };

    return {
        criarAssunto: _criarAssunto,
        removerAssunto: _removerAssunto,
        listarAssuntos: _listarAssuntos,
        listarDisciplinas: _listarDisciplinas
    };

});
