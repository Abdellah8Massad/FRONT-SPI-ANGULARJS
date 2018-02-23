App.controller('EnseignantController', ['$scope','$uibModal','$rootScope','EnseignantService', function ($scope,$uibModal,$rootScope,EnseignantService) {
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
            )
            .catch(
                notif("Enseignant","Enseignant déja exist","red")
            );
    }

    function supprimerEnseignant(code){
    	Promise.resolve(EnseignantService.deleteEnseignant(code)).then(function(){
    		fetchAllEnseignant();
            notif("Enseignant","Enseignant bien supprimer","green");
    		self.notification(true);
    	})
        .catch(
            notif("Enseignant","Enseignant utilisé dans d'autre table","red")
            );
    }


    function submit(){
            Promise.resolve(EnseignantService.getEnseignantMail(self.enseignant.emailPerso))
            .then(function(value) {
            if(value.length === 0){
            	createEnseignant(self.enseignant);
            	notif("Enseignant","Enseignant bien ajouter","green");
                reset();
            }
            else{
            	notif("Enseignant","mail déja exist","red");
            }
            });
            
    }

    function edit(form) {
    	Promise.resolve(createEnseignant(form)).then(function(value) {
    	self.modalInstance.close();
    	fetchAllEnseignant();
        self.getEnseignantmail(form.emailPerso);
        notif("Enseignant","Enseignant bien modifier","green");
    	})
        .catch(
            notif("Enseignant","Les informations incorrect","red")
            );
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

    function notif(titre,msg,color){
            self.modalOptions={
                                                headerText:titre,
                                                bodyText:msg,
                                                color:color
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
}]);