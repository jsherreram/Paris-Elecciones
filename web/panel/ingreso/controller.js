var app = angular.module("app", ['ngRoute', 'ui.bootstrap', 'app.global.services', 'app.services']);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);

//hacemos el ruteo de nuestra aplicación
app.config(function($routeProvider){
	$routeProvider.when("/", {
		templateUrl : "templates/Departamento.jsp",
		controller : "appControllerDepartamentos"
	})
	.when('/terceros-new/:idDpto', {
		templateUrl : "templates/terceros-new.html",
		controller : "EmpleadoController"
        })
	.when('/terceros-edit/:idDpto/:idEmpleado', {
		templateUrl : "templates/terceros-edit.html",
		controller : "EmpleadoEditar"
        })
        
	.when('/Terceros/:idDpto', {
		templateUrl : "templates/terceros.html",
		controller : "appControllerListar"
        })
	.when('/seleccionarArchivo/:idDpto/:idPersona/:idEmpleado', {
		templateUrl : "templates/seleccionarArchivo.jsp",
		controller : "appControllerFiles"
        })
        
	.when('/test', {
		templateUrl : "templates/test.html",
		controller : "PageCtrl"
        })
        
        
	.otherwise({ redirectTo : "/"});
});


app.filter('startFrom', function () {
	return function (input, start) {
		if (input) {
			start = +start;
			return input.slice(start);
		}
		return [];
	};
});


app.controller('PageCtrl', ['$scope', 'filterFilter', 'PersonaFactory', function ($scope, filterFilter, PersonaFactory) {

        PersonaFactory.listarPersonasJson('52', '0', function (data) {
           $scope.items = data;
           
            // create empty search model (object) to trigger $watch on update
            $scope.search = {};


            $scope.resetFilters = function () {
                    // needs to be a function or it won't trigger a $watch
                    $scope.search = {};
            };



            // pagination controls
            $scope.currentPage = 0;
            $scope.totalItems = $scope.items.length;
            $scope.entryLimit = 10; // items per page
            $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);


            // $watch search to update pagination
            $scope.$watch('search', function (newVal, oldVal) {
                    $scope.filtered = filterFilter($scope.items, newVal);
                    $scope.totalItems = $scope.filtered.length;
                    $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                    $scope.currentPage = 0;
            }, true);
           
           
        });


        
}]);

function leerCookie(nombre) {
    var lista = document.cookie.split(";");
    for (i in lista) {
        var busca = lista[i].search(nombre);
        if (busca > -1) {micookie=lista[i]}
        }
    var igual = micookie.indexOf("=");
    var valor = micookie.substring(igual+1);
    return valor;
}

app.controller("EmpleadoEditar", ['$scope', '$routeParams', 'Empleado', '$location', 'PersonaFactory', function ($scope, $routeParams, Empleado, $location, PersonaFactory){

        PersonaFactory.listarMunicipios($routeParams.idDpto, function (data) {
            
            if(data.municipio.length > 1){
                $scope.municipios = data.municipio;
            }else
                {
                    $scope.municipios = [];
                    $scope.municipios.push(data.municipio);
                }            
            
            //$scope.municipios = data.municipio;

            PersonaFactory.listarActividad(function (data) {
                $scope.actividades = data.actividad;
                
                $scope.tipoIdentificacion = [
                  {name:'CEDULA CIUDADANIA', id:'CC'},
                  {name:'CEDULA EXTRANJERIA', id:'CE'}
                ];

                $scope.estados = [
                  {name:'EN PROCESO', id:'0'},
                  {name:'PARA AUDITORIA', id:'2'},
                  {name:'INCONSISTENTE', id:'3'}
                ];


                PersonaFactory.getPersona($routeParams.idEmpleado, function (data) {
                    $scope.persona = data;

                    $scope.idEmpleado = $scope.persona.idEmpleado;
                    $scope.codigoDpto = $scope.persona.municipio.departamento.codigo;

                    for(k= 0; k < $scope.tipoIdentificacion.length; k++)
                    {
                        if($scope.tipoIdentificacion[k].id == $scope.persona.tipodoc)
                        {
                            $scope.tipoDoc = $scope.tipoIdentificacion[k];

                        }
                    }

                    for(m= 0; m < $scope.estados.length; m++)
                    {
                        if($scope.estados[m].id == $scope.persona.estado.codigoEstado)
                        {
                            $scope.estado = $scope.estados[m];
                        }
                    }


                    $scope.nrodoc = $scope.persona.nrodoc;
                    $scope.apellido1 = $scope.persona.apellido1;
                    $scope.apellido2 = $scope.persona.apellido2;
                    $scope.nombre1 = $scope.persona.nombre1;
                    $scope.nombre2 = $scope.persona.nombre2;
                    $scope.telefono = $scope.persona.telefono;
                    $scope.celular = $scope.persona.celular;
                    $scope.imagen = $scope.persona.imagen;
                    $scope.observacion = $scope.persona.observacion;
                    $scope.usuariocrea = $scope.persona.usuarioCrea;
                    
                    if ($scope.imagen == "true"){
                        $scope.display = "";
                    }else{
                        $scope.display = "none";
                    }
                    
                    
                    

                    for(i= 0; i < $scope.municipios.length; i++)
                    {
                        if($scope.municipios[i].codigoMunicipio == $scope.persona.municipio.codigoMunicipio)
                        {
                            $scope.codigoMunicipio = $scope.municipios[i];
                        }
                    }

                    $scope.direccion = $scope.persona.direccion;
                    $scope.email = $scope.persona.email;

                    for(j= 0; j < $scope.actividades.length; j++)
                    {
                        if($scope.actividades[j].codigo == $scope.persona.codigoActividad)
                        {
                            $scope.codigoActividad = $scope.actividades[j];
                        }
                    }

                });
                
                
            });


        });

        $scope.Save = function(){
          var usuario = leerCookie("APP-TOKEN");
          var empleado = new Empleado({
              apellido1:this.apellido1,
              apellido2:this.apellido2,
              celular:this.celular,
              codigoActividad:this.codigoActividad.codigo,
              direccion:this.direccion,
              email:this.email,
              estado:{"codigoEstado":this.estado.id,"descripcion":""},
              fechaCrea:"",
              fechaModifica:"",
              idEmpleado:$scope.idEmpleado,
              municipio:{codigoMunicipio:this.codigoMunicipio.codigoMunicipio,departamento:{codigo:this.codigoDpto,nombre:""},nombre:""},
              nombre1:this.nombre1,
              nombre2:this.nombre2,
              nrodoc:this.nrodoc,
              telefono:this.telefono,
              tipodoc:this.tipoDoc.id,
              usuarioCrea:$scope.usuariocrea,
              usuarioModifica:usuario,
              imagen:this.imagen,
              observacion:this.observacion
          });  
          
          empleado.$update(function (response){
                if (response.codigo == '200')
                    {
                        $location.path('/Terceros/'+$scope.codigoDpto);
                    }
                    else
                        {
                            $scope.error = response.descripcion;
                        };
          });
      };
}]);

app.controller("EmpleadoController", ['$scope', '$routeParams', 'Empleado', '$location', 'PersonaFactory', function ($scope, $routeParams, Empleado, $location, PersonaFactory){
      $scope.codigoDpto =  $routeParams.idDpto;
      $scope.codigoMunicipio = "001";
      
        $scope.tipoIdentificacion = [
          {name:'CEDULA CIUDADANIA', id:'CC'},
          {name:'CEDULA EXTRANJERIA', id:'CE'}
        ];
        $scope.tipoDoc = $scope.tipoIdentificacion[0];


            PersonaFactory.listarMunicipios($routeParams.idDpto, function (data) {
            
            //$scope.municipios = data.municipio;
            
            if(data.municipio.length > 1){
                $scope.municipios = data.municipio;
            }else
                {
                    $scope.municipios = [];
                    $scope.municipios.push(data.municipio);
                }            
            
            
            
            
	});
        
        
        PersonaFactory.listarActividad(function (data) {
            $scope.actividades = data.actividad;
	});

        $scope.Save = function(){
          var usuario = leerCookie("APP-TOKEN");  
          var empleado = new Empleado({
              apellido1:this.apellido1,
              apellido2:this.apellido2,
              celular:this.celular,
              codigoActividad:this.codigoActividad.codigo,
              direccion:this.direccion,
              email:this.email,
              estado:{"codigoEstado":"0","descripcion":"VACANTE"},
              fechaCrea:"",
              fechaModifica:"",
              idEmpleado:"",
              municipio:{codigoMunicipio:this.codigoMunicipio.codigoMunicipio,departamento:{codigo:this.codigoDpto,nombre:""},nombre:""},
              nombre1:this.nombre1,
              nombre2:this.nombre2,
              nrodoc:this.nrodoc,
              telefono:this.telefono,
              tipodoc:this.tipoDoc.id,
              usuarioCrea:usuario,
              usuarioModifica:" ",
              imagen:"false",
              display:""});  
          
          empleado.$save(function (response){
                if (response.codigo == '200')
                    {
                        $location.path('/terceros-edit/'+$scope.codigoDpto+'/'+response.id);
                        $scope.error = "Registro creado";
                    }
                    else
                        {
                            $scope.error = response.descripcion;
                        };
             
          });
          
      };
}]);

app.controller("appControllerFiles", ['$scope', '$routeParams', function ($scope, $routeParams){
      $scope.idPersona =  $routeParams.idPersona;
      $scope.idDpto = $routeParams.idDpto;
      $scope.idEmpleado = $routeParams.idEmpleado;
}]);

app.controller("appControllerListar", ['$scope', '$routeParams', 'PersonaFactory', function listar ($scope, $routeParams, PersonaFactory){

        $scope.codigoDepartamento = $routeParams.idDpto;

        $scope.listar = function(estado){
            
            PersonaFactory.listarPersonasJson($scope.codigoDepartamento, estado, function (data) {
                $scope.items = data;

                 // create empty search model (object) to trigger $watch on update
                 $scope.search = {};


                 $scope.resetFilters = function () {
                         // needs to be a function or it won't trigger a $watch
                         $scope.search = {};
                 };

                 // pagination controls
                 $scope.currentPage = 0;
                 $scope.totalItems = $scope.items.length;
                 $scope.entryLimit = 10; // items per page
                 $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);


                 // $watch search to update pagination
                 $scope.$watch('search', function (newVal, oldVal) {
                         $scope.filtered = filterFilter($scope.items, newVal);
                         $scope.totalItems = $scope.filtered.length;
                         $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                         $scope.currentPage = 0;
                 }, true);               
               
            });
        };
        
}]);

app.controller("appControllerDepartamentos", ['$scope', 'PersonaFactory','Empleado', function departamentos ($scope,PersonaFactory,Empleado){
 
        var idPrueba = 0;
        $scope.listar = function(usuario){
            
            Empleado.getEmpleadoPruebaSesion(function (data) {
            idPrueba = data.pruebaActual;
                PersonaFactory.listarDepartamentos(function (data) {
                    $scope.departamentoTmp = data.departamento;
                    PersonaFactory.listarDepartamentosDeUsuario(usuario,idPrueba, function (data) {

                        $scope.departamento = [];

                        if(data.usuarioDepartamento.length > 1)
                        {
                            $scope.dptosUsuario = data.usuarioDepartamento;

                            for(x= 0; x < $scope.departamentoTmp.length; x++)
                            {
                                for(y= 0; y < $scope.dptosUsuario.length; y++)
                                {
                                    if($scope.dptosUsuario[y].codigo == $scope.departamentoTmp[x].codigo)
                                        {
                                            $scope.dpto = {
                                                codigo:$scope.departamentoTmp[x].codigo,
                                                nombre:$scope.departamentoTmp[x].nombre
                                            };
                                            $scope.departamento.push($scope.dpto);
                                        }

                                }
                            }

                        }
                        else
                            {
                                $scope.dptosUsuario = data;

                                for(x= 0; x < $scope.departamentoTmp.length; x++)
                                {
                                    if($scope.dptosUsuario.usuarioDepartamento.codigo == $scope.departamentoTmp[x].codigo)
                                        {
                                            $scope.dpto = {
                                                codigo:$scope.departamentoTmp[x].codigo,
                                                nombre:$scope.departamentoTmp[x].nombre
                                            };
                                            $scope.departamento.push($scope.dpto);
                                        }
                                }

                            }


                    });    
                });
            });
        };
}]);
