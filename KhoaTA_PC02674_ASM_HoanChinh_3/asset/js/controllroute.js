let app = angular.module("myApp", ['ngRoute']);
app.config(function($routeProvider,$locationProvider){

    $routeProvider.when("/intro",{
        templateUrl:"Views/GT.html" 
    })
    .when("/contact",{
        templateUrl:"Views/lienHe.html"
    })
    .when("/gopy",{
        templateUrl:"Views/gopy.html",
        controller: "gopyCtrl"

    })
    .when("/hoidap",{
        templateUrl:"Views/hoiDap.html"
    })
    
    .when("/hsCanhan",{
        templateUrl:"Views/hoSoCaNhan.html",
        controller: "updateHSCtrl"

    })
    .when("/adminHsCanhan",{
        templateUrl:"Views/TongHoSoCaNhan.html",
        controller: "adminHSCtrl"

    })
    .when("/product",{
        templateUrl:"Views/product.html",
        controller: "productCtrl"
    })
    .when("/quiz/:id/:name/:logo", {
        templateUrl: "Views/test.html",
        controller: "quizCtrl"
    })
    .when("/test", {
        templateUrl: "Views/quiz1.html",
        controller: "testCtrl"
    })
    .when("/signup", {
        templateUrl: "Views/DangKy.html",
        controller: "dangkyCtrl"
    })
    .when("/login", {
        templateUrl: "Views/DangNhap.html",
        controller: "loginCtrl"
    })
    .when("/changePassword", {
        templateUrl: "Views/DoiMatKhau.html",
        controller: "dmkCtrl"
    })
    .when("/forgetPassword", {
        templateUrl: "Views/QuenMatKhau.html",
        controller: "qmkCtrl"
    })
    .otherwise({
        redirectTo: "/product"
    })
    

});


app.run(function($rootScope, $http, $timeout,$window) {

    $http.get("http://localhost:3000/Subjects").then(function(response) {
        $rootScope.products = response.data;
    });

    $http.get("http://localhost:3000/Students").then(function(response) {
        $rootScope.students = response.data;
    });
    
    $http.get("http://localhost:3000/Gopy").then(function(response) {
        $rootScope.gopys = response.data;
    });
    $rootScope.gopy =null;
    $rootScope.student= null;
    $rootScope.product = null;

    

});