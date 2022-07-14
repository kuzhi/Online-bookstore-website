app.controller("myCtrl", function($scope, $http, $rootScope) {
    $scope.tenTam = "Danh mục môn học";
    $scope.trangchu = function() {
        $scope.tenTam = "Danh mục môn học";
    }
    $scope.gioiThieu = function() {
        $scope.tenTam = "Giới thiệu";
    }
    $scope.lienHe = function() {
        $scope.tenTam = "Liên hệ";
    }
    $scope.gopY = function() {
        $scope.tenTam = "Góp ý";
    }
    $scope.hoiDap = function() {
        $scope.tenTam = "Hỏi đáp";
    }
    $scope.changePassword = function() {
        $scope.tenTam = "Đổi mật khẩu";
    }
    $scope.hsCaNhan = function() {
        $scope.tenTam = "Hồ sơ cá nhân";
    }
    $scope.login = function() {
        $scope.tenTam = "Đăng nhập";
    }
    $scope.signup = function() {
        $scope.tenTam = "Đăng ký";
    }
    $scope.forgetPassword = function() {
        $scope.tenTam = "Quên mật khẩu";
    }
    $scope.dangXuat = function() {
        $rootScope.clickLogin = true;
        $scope.tenTam = "Danh mục môn học";
        $rootScope.student= null;
        $rootScope.indexStudent = -1;
        
       
    }

    

    $scope.menu = 'Layout/menu.html';
    $scope.sideMenu = 'Layout/sideMenu.html';
    $scope.mainbody = 'Layout/mainbody.html';
    $scope.footer = 'Layout/footer.html';
    $rootScope.clickLogin = true;
    
});

