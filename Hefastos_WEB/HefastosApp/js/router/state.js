angulaAppModulo.config(function ($stateProvider, $urlRouterProvider) {
    
    // Rota padrão.
    $urlRouterProvider.otherwise("/");
    
    // Estados
    $stateProvider
        .state("cadastro", {
            url: '/cadastro',
            templateUrl: 'signUpPage.html'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'loginPage.html'
        })
        .state('tabelaProvas', {
            url: '/',
            templateUrl: 'tabelaProvas.html'
        })
        .state('tabelaAssuntos', {
            url: '/assuntos',
            templateUrl: 'tabelaAssuntos.html'
        })
        .state('tabelaDisciplinas', {
            url: '/disciplinas',
            templateUrl: 'tabelaDisciplinas.html'
        })
        .state('tabelaQuestoes', {
            url: '/questoes',
            templateUrl: 'tabelaQuestoes.html'
        })
});