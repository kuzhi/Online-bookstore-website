
app.controller('quizCtrl', ['$scope', '$routeParams', '$http', '$timeout', '$interval', '$rootScope', function ($scope, $routeParams, $http, $timeout, $interval, $rootScope, elem, attrs) {

    var promise;
    var check = false;
    $scope.name = $routeParams.name;
    $scope.logo = $routeParams.logo;

    $scope.monHoc = {};
    $scope.dsCauHoi = [];
    var id = $routeParams.id;
    $scope.inProgress = 0;
    $scope.anNut = 0;
    $scope.hienNut = 0;
    $scope.cungHien = 0;
    $scope.dsCTraLoi = [];
    $scope.dsCTraLoi.length = 11;
    $http.get("http://localhost:3000/Subjects").then(function (response) {
        var dsMonHoc = [];
        dsMonHoc = response.data;

        for (var i = 0; i < dsMonHoc.length; i++) {
            if (dsMonHoc[i].Id == id) {
                $scope.monHoc = dsMonHoc[i];

            }
        }
    });
    //kiem tra dang nhap 
    $scope.checkID = function(){
       

        if($rootScope.student ==null){
            Swal.fire({
                icon: 'error',
                title: 'Bạn chưa đăng nhập!',
                text: 'Hãy quay lại sau khi đăng nhập!'
            })
        }
        else{ 
           
            Swal.fire({
            title: 'Bắt đầu thi?',
            text: "Let's go!",
            icon: 'info',
            
        })
        check=true; 
        }
    }
    // Đồng hồ
    // $rootScope.ss = 59;
    // $rootScope.mm = 14;

    $scope.start = function () {
        if(check){
            $http.get('asset/js/db/Quizs/' + id + '.js').then(function (response) {
                $scope.dsCauHoi = shuffleArray(response.data, 10);
                $scope.showmark = 0;
            }, function(response){console.log('lỗi')})
            $scope.dsCauHoi = [0];
            $scope.inProgress = 1;
            $scope.anNut = 0;
            $scope.hienNut = 1;
        }
        
    }

    $scope.timer = 900;
    
    $scope.batdau = function () {
          promise = $interval(function() {

            if ($scope.timer > 0) {
                $scope.timer -= 1;
            } else if ($scope.timer == 0) {
                $interval.cancel(promise);
                $scope.submit();
            }
        }, 1000);
        
    }

    $scope.stop= function(){
        $interval.cancel(promise)
    }

    $http.get('asset/js/db/Quizs/' + id + '.js').then(function (response) {
        $scope.dsCauHoi = shuffleArray(response.data, 10);
        $scope.showmark = 0;
    }, function(response){console.log('lỗi')})

    $scope.submit = function () {
        $scope.mark = 0;
        $scope.timer=900;
        $scope.stop();
        $scope.inProgress = 3;
        for (var i = 0; i < $scope.dsCauHoi.length; i++) {
            if ($scope.dsCTraLoi[i] != null) {
                if ($scope.dsCTraLoi[i] == $scope.dsCauHoi[i].AnswerId) {
                    $scope.mark += $scope.dsCauHoi[i].Marks;
                }
            }
        }
        // console.log($scope.dsCTraLoi);
        // console.log($scope.checkUndefined);
        // $scope.inProgress = false;
        // $scope.showmark = mark;

    }
     // stops the interval when the scope is destroyed,
    // this usually happens when a route is changed and 
    // the ItemsController $scope gets destroyed. The
    // destruction of the ItemsController scope does not
    // guarantee the stopping of any intervals, you must
    // be responsible for stopping it when the scope is
    // is destroyed.
    $scope.$on('$destroy', function() {
      $scope.stop();
    });
    $scope.checkUndefined = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    $scope.addAnswer = function (index, answerId) {
        $scope.dsCTraLoi[index] = answerId;
        // $scope.dsCTraLoi[index] = answerName;
        $scope.checkUndefined[index] = 1;
    }
    // $scope.arrayAns = [];
    // $scope.getAns = function(index, ansName) {
    //     $scope.arrayAns[index] = ansName;
    //     $scope.checkUndefined[index] = 1;
    // }

    

    $scope.begin = 0;
    $scope.prev = function () {
        if ($scope.begin > 0) {
            $scope.begin--;
            $scope.cungHien = 1;
            if ($scope.dsCTraLoi[$scope.begin] != null) {
                $scope.answerId = $scope.dsCTraLoi[$scope.begin];
                // $scope.ansName = $scope.arrayAns[$scope.begin];
            }
        }
        if ($scope.begin == 0) {
            $scope.cungHien = 0;
            $scope.anNut = 0;
            $scope.hienNut = 1;
        }
    }
    
    $scope.next = function () {
        if ($scope.begin < $scope.dsCauHoi.length - 1) {
            $scope.begin++;
            $scope.cungHien = 1;
            if ($scope.dsCTraLoi[$scope.begin] != null) {
                $scope.answerId = $scope.dsCTraLoi[$scope.begin];
                // $scope.ansName = $scope.arrayAns[$scope.begin];
            }
        }

        if ($scope.begin == 9) {
            $scope.cungHien = 0;
            $scope.anNut = 1;
            $scope.hienNut = 0;
        }
    }

    // hàm ramdom
    function shuffleArray(array, number) {
        for (var i = array.length - 1; i > 0; i--) {
            var j = Math.floor(Math.random() * (i + 1));
            var temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        array = array.slice(0, number);
        return array;
    };

    $scope.tong = function (){
        $scope.inProgress = 4;
    }
}])

