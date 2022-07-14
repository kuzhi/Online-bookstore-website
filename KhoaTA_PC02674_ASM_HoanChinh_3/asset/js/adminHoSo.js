app.controller('adminHSCtrl',function($http,$rootScope,$scope){
    var data ;
    var url1= "http://localhost:3000/Students";
    $scope.clear=function(){
        $scope.student = {};
        $scope.index = -1;
    }
    $scope.insert=function(){
        $scope.students.push(angular.copy($scope.student));
        data = {
            id: Math.floor(Math.random()*1000),
            username: $scope.student.username,
            password: $scope.student.password,
            fullname: $scope.student.fullname,
            email: $scope.student.email,
            gender: $scope.student.gender,
            birthday: $scope.student.birthday,
            schoolfee: $scope.student.schoolfee,
            marks: "0"
        }
        console.log(data);
        
       $http.post(url1, JSON.stringify(data))
        .then(function(respone){
            console.log(respone.data)
            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Thêm username thành công',
                
            });
        })
        $scope.clear();
    }
    $scope.save=function(){
        $scope.students[$scope.index] = angular.copy($scope.student);
        Swal.fire({
            icon: 'success',
            title: 'Thành công!',
            text: 'Cập nhật username thành công',
            
        });
        

    }
    $scope.delete=function(){
        $scope.students.splice($scope.index, 1);
        $scope.clear();
        Swal.fire({
            icon: 'success',
            title: 'Thành công!',
            text: 'Xóa username thành công',
        });
    }
    $scope.reset=function(){
        if ($scope.index == -1){
            $scope.clear();
        }else{
            $scope.edit($scope.index);
        }
    }
    $scope.edit=function(index){
        $scope.index = index;
        $scope.student = angular.copy($scope.students[index]);
    }
})

