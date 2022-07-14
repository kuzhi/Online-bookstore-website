
app.controller('qmkCtrl',function($scope, $http, $rootScope ){
   
        $scope.getPass = function() {
            // alert("SUBMIT "+$scope.regObj.username);
            var ck = true;
            angular.forEach($rootScope.students, function(item) {
                 
                if ((item.username == $scope.student.username) && (item.email == $scope.student.email)) {
                    
                    ck = false;
                     
                    $scope.student.password= item.password
                    return;
                }
            });
            if (ck) {
                Swal.fire({
                    icon: 'error',
                    title: 'Quên mật khẩu',
                    text: 'Sai địa chỉ email hoặc tên đăng nhập',
                });
                $scope.student.password =""
                $scope.student.username =""
                $scope.student.email =""
            }
        
        };

});

