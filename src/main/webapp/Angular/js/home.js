/**
 * Created by Vaishampayan Reddy on 4/14/2016.
 */
cmpe281.controller('homeController', function($scope, $routeParams, $http) {
    var myCenter=new google.maps.LatLng(37.3352,-121.8811);

    function initialize()
    {
        var mapProp = {
            center:myCenter,
            zoom:14,
            mapTypeId:google.maps.MapTypeId.ROADMAP
        };

        var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
    }

    google.maps.event.addDomListener(window, 'load', initialize);
});