App.controller('EnseignantController', ['$scope','$uibModal','EnseignantService', function ($scope,$uibModal,EnseignantService) {
	var self = this;

    self.settings = {
                        currentPage: 0,
                        offset: 0,
                        pageLimit: 5,
                        pageLimits: ['10', '50', '100']
                        };

    self.enseignant = {
    		noEnseignant: null,
    		type: "",
    		codePostal: "",
    		emailPerso: "",
    		emailUbo: "",
    		mobile: "",
    		adresse: "",
    		nom:"",
    		pays:"",
    		prenom:"",
    		sexe:"",
    		telephone:"",
    		ville:""
                     }
    
    self.enseignants = [];

    self.detail = {
    		noEnseignant: null,
    		type: "",
    		codePostal: "",
    		emailPerso: "",
    		emailUbo: "",
    		mobile: "",
    		adresse: "",
    		nom:"",
    		pays:"",
    		prenom:"",
    		sexe:"",
    		telephone:"",
    		ville:""
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
    self.fetchall=fetchAllEnseignant();
    
    
    fetchAllEnseignant();
    
    self.sort = function(keyname){
        self.sortKey = keyname;  
        self.reverse = !self.reverse;
    }
    
    self.ajoutervisible = function(){
    	self.ajouterDiv = !self.ajouterDiv;
    }

    function fetchAllEnseignant(){
    	EnseignantService.fetchAllEnseignant()
            .then(
                function (d) {
                    self.enseignants = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }

    this.getEnseignant = function(nom) {
    	self.notificationClose();
        EnseignantService.getEnseignant(nom)
            .then(
                function (d) {
                    self.detail = d;
                    self.modalInstance = $uibModal.open({
                    	 scope:$scope,
                  		 templateUrl: 'html/enseignantEdite.html',
                  		 size: 'lg'
                  		 })
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }
    
    this.getEnseignantmail = function(mail) {
    	self.notificationClose();
        EnseignantService.getEnseignantMail(mail)
            .then(
                function (d) {
                    self.detail = d;
                    console.log(d);
                    self.modalInstance = $uibModal.open({
                    	 scope:$scope,
                  		 templateUrl: 'html/enseignantEdite.html',
                  		 size: 'lg'
                  		 })
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }
    
    function createEnseignant(enseignanat) {
    	EnseignantService.createEnseignant(enseignanat)
            .then(
            	fetchAllEnseignant,
                function (errResponse) {
                    console.error('Error while creating User');
                }
            );
    }

    function supprimerEnseignant(code){
    	Promise.resolve(EnseignantService.deleteEnseignant(code)).then(function(){
    		fetchAllEnseignant();
    		self.valider.msg = "Formation bien supprimer";
    		self.notification(true);
    	});
    }


    function submit(){
            Promise.resolve(EnseignantService.getEnseignantMail(self.enseignant.emailPerso))
            .then(function(value) {
            if(value.length === 0){
            	createEnseignant(self.enseignant);
            	self.notification(true);
                self.valider.msg = "Enseignant bien ajouter";
                reset();
            }
            else{
            	self.notification(false);
                self.errors.msg = "mail déja exist";
            }
            });
            
    }

    function edit(form) {
    	Promise.resolve(createEnseignant(form)).then(function(value) {
    	self.modalInstance.close();
    	fetchAllEnseignant();
        self.getEnseignantmail(form.emailPerso);
        self.notification(true);
        self.valider.msg = "Enseignant bien modifier";
    	});
    }

    function remove(id) {
    	supprimerEnseignant(id);
        fetchAllEnseignant();
    }

    function reset() {
        self.enseignant = {
        		noEnseignant: null,
        		type: "",
        		codePostal: "",
        		emailPerso: "",
        		emailUbo: "",
        		mobile: "",
        		adresse: "",
        		nom:"",
        		pays:"",
        		prenom:"",
        		sexe:"",
        		telephone:"",
        		ville:""
                     };
        $scope.myForm.$setPristine();
    }
    
    self.notification= function(a){
    	if(a){
    		self.errors.classe = "panel panel-default error_classe_hidden";
            self.valider.classe = "panel panel-default valider_classe";
    	}
    	else{
    		self.valider.classe = "panel panel-default valider_classe_hidden";
            self.errors.classe = "panel panel-default error_classe";
    	}
    }
    
    self.notificationClose=function(){
    		self.errors.classe = "panel panel-default error_classe_hidden";
            self.valider.classe = "panel panel-default valider_classe_hidden";
    }
}]);