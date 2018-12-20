app.controller("indexController",function ($scope,indexService) {
     //要和页面中的方法名一致
    $scope.showLoginName=function () {
        indexService.username().success(function (response) {
            $scope.loginName=response.loginName;//要和页面中的变量名一致
        })
    }
})