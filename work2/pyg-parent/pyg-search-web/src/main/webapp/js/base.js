var app = angular.module('pinyougou', []);

//信任html服务
app.filter('trustHtml',['$sce',function($sce){
    return function(data){
        return $sce.trustAsHtml(data);
    }
}]);
