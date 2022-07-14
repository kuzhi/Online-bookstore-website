app.controller('updateHSCtrl', function($scope,$rootScope){
    $scope.save = function(){
        $rootScope.students[$rootScope.indexStudent] = angular.copy($rootScope.student);
        Swal.fire({
            icon: 'success',
            title: 'Cập nhật thông tin cá nhân thành công!',
        });
    }
})


