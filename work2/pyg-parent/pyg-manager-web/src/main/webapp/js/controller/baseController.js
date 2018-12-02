app.controller("baseController",function($scope) {

    //分页控件配置currentPage：当前页
    $scope.paginationConf = {
        currentPage : 1,
        totalItems : 10,
        itemsPerPage : 10,
        perPageOptions : [ 10, 20, 30, 40, 50 ],
        onChange : function() {
            //重新加载
            $scope.reloadList();
        }
    };

    //搜索后刷新
    $scope.reloadList=function () {
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);

    }

    //更新复选
    //checkbox选中之后当前id存放到这个集合
    $scope.selectIds = [];
    $scope.updateSelection=function ($event,id) {
        if($event.target.checked){//如果是被选中,则增加到数组
            $scope.selectIds.push(id);
        }else {
            var idx=$scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx,1);//删除
        }

    }

    //提取json字符串数据中某个属性，返回拼接字符串 逗号分隔
    $scope.jsonToString=function(jsonString,key){
        var json=JSON.parse(jsonString);//将json字符串转换为json对象
        var value="";
        for(var i=0;i<json.length;i++){
            if(i>0){
                value+=","
            }
            value+=json[i][key];
        }
        return value;
    }


});