<div class="content animate-panel" >
    <div class="row">
        <div class="col-lg-12">
            <div class="hpanel">
                <div class="panel-body">                   
                    <h2 class="font-light m-b-xs">
                        Nombramiento Por Sitio
                    </h2>
                </div>
            </div>
        </div>
    </div>
    
    <div class="stats-label text-color">
        <span class="font-extra-bold font-uppercase"></span>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
              <div class="panel-heading">
              </div>
              <div class="panel-body">

                <form class="form-inline">
                    
                    <div class="row">   
                        <div class="form-group">
                                <label class="control-label col-md-2" for="sitio">Sitio</label>
                                <div class="col-md-10">
                                   <select id="sitio" name="sitio" ng-model ="sitio" required="required" ng-options="sit.nombreSitio for sit in sitiossincierre"></select>
                                </div>
                        </div>
                    </div>

                    <div class="row">   
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-2">  
                                <a href="#/Nombramiento/{{sitio.id}}/{{idprueba}}" class="btn btn-sm btn-primary">Nombrar</a>
                            </div>
                        </div>
                    </div>
                </form>
             </div>
            </div>
        </div>
    </div>
</div>