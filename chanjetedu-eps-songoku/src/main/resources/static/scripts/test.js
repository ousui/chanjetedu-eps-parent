(function (w) {
    setTimeout(function () {
        document.getElementById('title').innerHTML += ' - v' + new Date().getTime();
    }, 1000)
})(window);