function removeView(pid){
    console.log("we are in the removeView()");
    
    $.ajax({
        url: "RemoveViewerTracking",
        type: "GET",
        data: "productID="+pid, 
        async : false,
        cache: false,
    success: function(msg){
         //alert( "Data Saved: " + msg );
    },
    error: function () {
        alert("Failure to remove a viewer");
    }
    });     
}


function addView(pid){ 
    console.log("we are in the addView()");
    $.ajax({
        url: "AddViewerTracking",
        type: "GET",
        data: "productID="+pid, 
        async : false,
        cache: false,
    success: function(msg){
//         alert( "Data Saved: " + msg + " and cid: " + pid);
           document.getElementById("view_data").innerHTML = msg;

    },
    error: function () {
        alert("Failure to add a viewer");
    }
    });   
}
