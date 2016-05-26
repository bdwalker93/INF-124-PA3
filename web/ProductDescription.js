function removeView(){
    console.log("we are in the removeView()");
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            var data = xhr.responseText;
            alert(data);
        }
    }
    xhr.open('GET', '/RemoveViewerTracking', true);
    xhr.send(null);
    
}


