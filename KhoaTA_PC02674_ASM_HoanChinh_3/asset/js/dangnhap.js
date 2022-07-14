
app.controller('loginCtrl',function($scope, $http, $window, $rootScope){
 
    $rootScope.Admin= false;
    $scope.login = function() {
        var ig = true;
        $rootScope.students.forEach(st => {
            if (st.username == $scope.student.username) {
                if (st.password == $scope.student.password) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Đăng nhập thành công!',
                        text: 'Quay lại trang chủ!',
                        showConfirmButton: false,
                        closeOnClickOutside: false,
                        allowOutsideClick: false,
                        timer: 1600
                    });
                    $rootScope.indexStudent = st.index;
                    $rootScope.student = st;
                    $window.location.href = '#!product'
                    $rootScope.login = false;
                    $rootScope.clickLogin = false;
                    //admin hoặc user học viên
                    if($scope.student.username=="admin"){
                        $rootScope.Admin =true;
                    }

                    ig = false;
                    return;
                };
            };
        });
        if (ig == true) {
            Swal.fire({
                icon: 'error',
                title: 'Đăng nhập thất bại!',
                text: 'Nhập lại!'
            });
        }
    };

});


