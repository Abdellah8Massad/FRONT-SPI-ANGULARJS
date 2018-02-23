App.controller('FormationController', ['$scope','$rootScope','$uibModal','FormationService', function ($scope,$rootScope,$uibModal,FormationService) {
	var self = this;

    self.settings = {
                        currentPage: 0,
                        offset: 0,
                        pageLimit: 5,
                        pageLimits: ['10', '50', '100']
                        };

    self.formation = {
                        codeFormation: "",
                        debutAccreditation: "",
                        diplome: "",
                        doubleDiplome: "",
                        finAccreditation: "",
                        n0Annee: null,
                        nomFormation: ""
                     }
    
    self.formations = [];

    self.detail = {
                        codeFormation: "",
                        debutAccreditation: "",
                        diplome: "",
                        doubleDiplome: "",
                        finAccreditation: "",
                        n0Annee: null,
                        nomFormation: ""
                     }
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.ajouterDiv=false;
    self.reset = reset;
    self.errors = {
                        classe: "panel panel-default error_classe_hidden",
                        msg: "",
                        trues:true
                     }
    self.valider = {
                        classe: "panel panel-default valider_classe_hidden",
                        msg: ""
                    }    
    self.fetchall=fetchAllFormation();
    
    
    fetchAllFormation();
    
    self.sort = function(keyname){
        self.sortKey = keyname;  
        self.reverse = !self.reverse;
    }
    
    self.ajoutervisible = function(){
    	self.ajouterDiv = !self.ajouterDiv;
    }

    function fetchAllFormation() {
        FormationService.fetchAllFormation()
            .then(
                function (d) {
                    self.formations = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }

    this.getFormation = function(nom) {
        self.div_class="";
        FormationService.getFormation(nom)
            .then(
                function (d) {
                    self.detail = d;
                    self.detail.debutAccreditation = new Date(d.debutAccreditation);
                    self.modalInstance = $uibModal.open({
                    	 scope:$scope,
                  		 templateUrl: 'html/formationEdite.html',
                  		 size: 'lg'
                  		 })
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }

    function createFormation(formation) {
        FormationService.createFormation(formation)
            .then(
                fetchAllFormation,
                function (errResponse) {
                    console.error('Error while creating User');
                }
            )
            .catch(function(){
                            self.modalOptions={
                                                headerText:"Error Formation",
                                                bodyText:"les informations saisie incorrect",
                                                color:"red"
                                              }
                            $rootScope.modalOptions=self.modalOptions;

                            self.modalOptionsTemp = $uibModal.open({
                                             scope:$scope,
                                             templateUrl: 'html/error/notificationError.html',
                                             controller: 'MessageController',
                                             controllerAs: 'ctrl',
                                             size: 'lg'
                                             })
                            $rootScope.modalOptionsTemp=self.modalOptionsTemp;
            });
    }

    function supprimerFormation(code){
    	Promise.resolve(FormationService.deleteFormation(code)).then(function(){
    			fetchAllFormation();
    			self.errors.classe = "panel panel-default error_classe_hidden";
                self.valider.classe = "panel panel-default valider_classe";
                self.valider.msg = "Formation bien supprimer";
    			
    	})
        .catch(function(){
                            self.modalOptions={
                                                headerText:"Error Formation",
                                                bodyText:"Formation utilisé dans d'autre table",
                                                color:"red"
                                              }
                            $rootScope.modalOptions=self.modalOptions;

                            self.modalOptionsTemp = $uibModal.open({
                                             scope:$scope,
                                             templateUrl: 'html/error/notificationError.html',
                                             controller: 'MessageController',
                                             controllerAs: 'ctrl',
                                             size: 'lg'
                                             })
                            $rootScope.modalOptionsTemp=self.modalOptionsTemp;
        });
    }

    function submit(){
            Promise.resolve(FormationService.getFormationCode(self.formation.codeFormation))
            .then(function(value) {
            if(value.length === 0){
                createFormation(self.formation);
                self.modalOptions={
                                                headerText:"Formation",
                                                bodyText:"Formation bien ajouter",
                                                color:"green"
                                              }
                            $rootScope.modalOptions=self.modalOptions;

                            self.modalOptionsTemp = $uibModal.open({
                                             scope:$scope,
                                             templateUrl: 'html/error/notificationError.html',
                                             controller: 'MessageController',
                                             controllerAs: 'ctrl',
                                             size: 'lg'
                                             })
                            $rootScope.modalOptionsTemp=self.modalOptionsTemp;
                reset();
            }
            else{
                self.modalOptions={
                                                headerText:"Error Formation",
                                                bodyText:"Formation déja exist",
                                                color:"red"
                                              }
                            $rootScope.modalOptions=self.modalOptions;

                            self.modalOptionsTemp = $uibModal.open({
                                             scope:$scope,
                                             templateUrl: 'html/error/notificationError.html',
                                             controller: 'MessageController',
                                             controllerAs: 'ctrl',
                                             size: 'lg'
                                             })
                            $rootScope.modalOptionsTemp=self.modalOptionsTemp;
            }
            });
            
    }

    function edit(form) {
    	Promise.resolve(createFormation(form)).then(function() {
        fetchAllFormation();
        self.getFormation(form.nomFormation);
        self.modalOptions={
                                                headerText:"Formation",
                                                bodyText:"Formation bien modifier",
                                                color:"green"
                                              }
                            $rootScope.modalOptions=self.modalOptions;

                            self.modalOptionsTemp = $uibModal.open({
                                             scope:$scope,
                                             templateUrl: 'html/error/notificationError.html',
                                             controller: 'MessageController',
                                             controllerAs: 'ctrl',
                                             size: 'lg'
                                             })
                            $rootScope.modalOptionsTemp=self.modalOptionsTemp;
    	});
    }

    function remove(id) {
        supprimerFormation(id);
        fetchAllFormation();
    }

    function reset() {
        self.formation = {
                        codeFormation: "",
                        debutAccreditation: "",
                        diplome: "",
                        doubleDiplome: "",
                        finAccreditation: "",
                        n0Annee: null,
                        nomFormation: ""
                     };
        $scope.myForm.$setPristine();
    }
    
    
}]);