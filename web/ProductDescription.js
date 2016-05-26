function removeView(pid){
    console.log("we are in the removeView()");
//    
//    $.ajax({
//        url: 'RemoveViewerTracking',
//        type: 'GET',
//        data: jQuery.param({productID: pid}) ,
//        contentType: 'application/json; charset=utf-8',
//        success: function (response) {
//            alert("sucess" + response.status);
//            return false;
//        },
//        error: function () {
//            alert("failure");
//            return false;
//        }
//    }); 
    
    $.ajax({
        url: "RemoveViewerTracking",
        type: "GET",
        data: "productID="+pid, 
        async : false,
    success: function(msg){
         alert( "Data Saved: " + msg );
    },
    error: function () {
        alert("failure");
    }
    });     
}


