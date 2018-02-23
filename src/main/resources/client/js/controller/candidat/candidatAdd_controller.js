App.controller('CandidatAddController', ['$scope','$uibModal','$location','CandidatService', function ($scope,$uibModal,$location,CandidatService) {
	
    var self = this;
    var ID = function () {
					  	  return '_' + Math.random().toString(36).substr(2, 9);
					     };

		self.candidat =	{
						noCandidat:"",
						sexe:"",
						nom:"",
						prenom:"",
						dateNaissance:"",
						lieuNaissance:"",
						nationalite:"",
						paysOrigine:"",
						ville:"",
						email:"",
						adresse:"",
						anneeUniversitaire:"",
						universiteOrigine:"",
						codeFormation:""
						}		     
	
	self.AddCandidat=function(){
							self.candidat.noCandidat=ID();
							self.candidat.codeFormation = "M2DOSI";
							self.candidat.anneeUniversitaire = "2013-2014";
					     	CandidatService.createCandidat(self.candidat)
					         .then(
					             function () {
					             	
					             });
					     	}
	
	self.retourner = function(){
        var url = '/candidat';
        $location.path(url);
        }
	
	
}]);