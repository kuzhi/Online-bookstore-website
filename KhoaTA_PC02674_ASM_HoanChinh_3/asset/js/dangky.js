

app.controller('dangkyCtrl',function($scope,$http,$rootScope){
    
    var kiemtraUsername = false;
    var kiemtraEmail = false;
    
    $scope.dangKy = function(username,
        password,
        fullname,
        email,
        gender,
        date,fee){
        if($scope.repassword == $scope.password){
            
                 $rootScope.students.forEach(st => {
                     


                        if(st.username == $scope.username){
                            kiemtraUsername=true;
                            Swal.fire({
                                icon: 'warning',
                                title: 'Opps!',
                                text: 'Tên đăng nhập này đã được sử dụng',
                                
                            });
                        }
                        
                        if(st.email == $scope.email){
                            kiemtraEmail=true;
                            Swal.fire({
                                icon: 'warning',
                                title: 'Opps!',
                                text: 'Email này đã được sử dụng',
                                
                            });
                        }
                        
                       
                        })


                        if((kiemtraUsername==false) && (kiemtraEmail==false)){

                            var data = {
                                id: Math.floor(Math.random()*1000),
                                username: username,
                                password: password,
                                fullname: fullname,
                                email: email,
                                gender: gender,
                                birthday: date,
                                schoolfee: fee,
                                marks: "0"
                            }
                            $http.post("http://localhost:3000/Students", JSON.stringify(data)).then(function(respone,$window){
                            Swal.fire({
                                        icon: 'success',
                                        title: 'Đăng Ký thành công!',
                                        text: 'Quay lại trang chủ!',
                                        
                                    });
                                $window.location.href = '#!product'

                            },function(error){
                                Swal.fire({
                                            icon: 'warning',
                                            title: 'Đăng ký không thành công!',
                                            
                                        });
                            })
                        }
        }
    else{
         Swal.fire({
                        icon: 'error',
                        title: 'Opps!',
                        text: 'Mật khẩu và xác nhận mật khẩu không giống nhau',
                        
                    });
    }
        

    $scope.username = "";
    $scope.repassword = "";
    $scope.password = "";
    $scope.fullname = "";
    $scope.email = "";
    $scope.gender = "";
    $scope.date = "";
    $scope.fee = "";

    kiemtraUsername  =false;
    kiemtraEmail = false;
    }
});
