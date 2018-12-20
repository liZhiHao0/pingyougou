app.controller("brandController",function ($scope,$controller,brandService) {

    $controller("baseController",{$scope:$scope});

    $scope.findAll=function () {
        brandService.findAll().success(
            function (response) {
                $scope.list=response;
            }
        );
    }


    $scope.findByPage=function (pageNum,pageSize) {
        brandService.findByPage(pageNum,pageSize).success(function (response) {
            $scope.paginationConf.totalItems=response.total;
            $scope.list=response.rows;
        });


    }

    //新增
    $scope.save=function () {
        var obj=null;
        if($scope.entity.id!=null){
            obj=brandService.update($scope.entity);
        }else {
            obj=brandService.add($scope.entity);
        }

        obj.success(
            function(response) {
                if(response.success){
                    $scope.reloadList();
                }else {
                    alert(response.message)
                }
            }
        );
    }

    //查询实体
    $scope.findOne=function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity=response;
            }
        );

    }


    $scope.delete=function () {
        //获取选中的复选框
        brandService.delete($scope.selectIds).success(
            function (response) {
                if(response.success){
                    $scope.reloadList();//刷新列表

                }else {
                    alert(response.message);
                }

            });

    }

    $scope.searchEntity={};//定义搜索对象
    //条件查询
    $scope.search=function (pageNum,pageSize) {
        brandService.search(pageNum,pageSize,$scope.searchEntity).success(
            function (response) {
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.rows;//给列表变量赋值
            }
        );

    }

});