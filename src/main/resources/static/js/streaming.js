(function () {
    var video = document.getElementById('video');
    var videoUrl = window.URL || window.webkitURL;
    navigator.getMedia = navigator.getUserMedia ||
        navigator.webkitGetUserMedia || navigator.mozGetUserMedia
        || navigator.msGetUserMedia;

    navigator.getMedia({
        video: true,
        audio: true,
    }, function (stream) {
        video.srcObject = stream;
        video.play();
    }, function (err) {
        console.log(err);
    });
})();