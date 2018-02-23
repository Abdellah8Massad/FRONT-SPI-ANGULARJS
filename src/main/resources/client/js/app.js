var App = angular.module('myApp', ['ngRoute','angularUtils.directives.dirPagination','ui.bootstrap']);
App.config(function($routeProvider) {
    $routeProvider
    .when("/formation", {
    	 templateUrl : "html/formation.html",
         controller : "FormationController"
    })
    .when("/enseignant", {
        templateUrl : "html/enseignant.html",
        controller : "EnseignantController"
    })
    .when("/candidat", {
        templateUrl : "html/candidat.html",
        controller : "CandidatListController"
    })
    .when("/candidatEdit", {
        templateUrl : "html/candidatedit.html",
        controller : "CandidatEditController"
    })
    .when("/candidatAdd", {
        templateUrl : "html/candidatadd.html",
        controller : "CandidatAddController"
    })
    .otherwise({
        templateUrl : "html/boardre.html",
        controller : "AdminController"
    });
});