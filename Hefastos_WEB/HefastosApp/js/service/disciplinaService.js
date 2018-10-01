angulaAppModulo.factory("DisciplinaService", function ($http) {

    var urlBase = "http://34.209.165.216:8080/RestEasyApp";

    var _criarDisciplina = function (disciplina) {
		return $http.post(urlBase + "/disciplina/inserir", disciplina);
	};
    
    var _deletarDisciplina = function (disciplina){
        return $http.post(urlBase + "/disciplina/deletar", disciplina);
    };
    
     var _listarDisciplinas = function (disciplina){
        return $http.get(urlBase + "/disciplina/listar");
    };

    return {
        criarDisciplinas: _criarDisciplina,
        deletarDisciplina: _deletarDisciplina,
        listarDisciplinas: _listarDisciplinas
    };

});
