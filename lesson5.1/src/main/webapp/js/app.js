window.notifySuccess = function (message) {
    $.notify(message, {position: "bottom right", className: "success"})
};

window.notifyError = function (message) {
    $.notify(message, {position: "bottom right", className: "error"})
};

window.notify = function (message) {
    $.notify(message, {position: "bottom right", className: "info"})
};

window.ajax = function ({
                            errorHandle = false,
                            $error,
                            type = "POST",
                            url = "",
                            dataType = "JSON",
                            data,
                            success
                        }) {
    if (errorHandle) {
        success = function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    }
    return $.ajax({url, type, data, dataType, success})
};

