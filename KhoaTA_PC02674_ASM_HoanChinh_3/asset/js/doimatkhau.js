

app.controller('dmkCtrl',function($scope,$http, $rootScope){
    
    $scope.save = function(){
        if($rootScope.student.password == $scope.oldpass){{
            
            if($rootScope.student.password == $scope.newpassword){
                alert("mat khau moi trung voi mạt khẩu cũ");
            }
            else{
                $rootScope.student.password = $scope.newpassword
                $rootScope.students[$rootScope.indexStudent] = angular.copy($rootScope.student);
             

                Swal.fire({
                    title: 'Đổi mật khẩu thành công',
                    text: "Bạn có muốn quay lại trang chủ!",
                    icon: 'success',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Có!',
                    cancelButtonText: 'Không'
                }).then((result) => {
                    if (result.value) {
                        window.location.href = "#!product";
                    }
                })
            }

        }
        }
        else{
         alert("Mật khẩu cũ không đúng!") 
        }
        
    }
})
