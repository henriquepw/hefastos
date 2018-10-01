angulaAppModulo.factory("ProfessorService", function ($http) {

    var urlBase = "http://34.209.165.216:8080/RestEasyApp";

    var _cadastrarProfessor = function (professor) {
		return $http.post(urlBase + "/professor/inserir", professor);
	};
	
    var _loginProfessor = function (professor) {
		return $http.post(urlBase + "/professor/login", professor);
	};

    return {
        cadastrarProfessor: _cadastrarProfessor,
        loginProfessor: _loginProfessor
    };

});
