app.controller('gopyCtrl',function($scope,$rootScope,$http){
    

    $scope.submit = function(gopy){
        var data = {
            "id": Math.floor(Math.random()*1000),
            "name": $scope.gopy.fullname,
            "email": $scope.gopy.email,
            "sdt": $scope.gopy.sdt,
            "tieude": $scope.gopy.tieude,
            "cmt": $scope.gopy.cmt
        }

        $http.post("http://localhost:3000/Gopy",JSON.stringify(data)).then(function(response){
            Swal.fire({
                icon: 'success',
                title: 'Gửi góp ý thành công!',
                text: 'Cám ơn bạn đã góp ý cho website hoàn thiện hơn!',
                
            });
        },function(error){
            Swal.fire({
                icon: 'error',
                title: 'Gửi góp ý thất bại!',
                text: 'Quay lại trang chủ!',
                
            }); 
        })
    }
})

