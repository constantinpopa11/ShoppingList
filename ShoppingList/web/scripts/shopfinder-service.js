var newShopsOnly = false;
function getUserCoordinates(option) {
    newShopsOnly = option;
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(findNearShops);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}

function findNearShops(position) {
    $.ajax({
        url: "FindNearShops",
        type: "get", //send it through get method
        data: {
            userLong: position.coords.longitude,
            userLat: position.coords.latitude,
            newShopsOnly: newShopsOnly
        },
        success: function (response) {

            if (newShopsOnly && response === "noNewShops") {
                //do nothing
            } else if (newShopsOnly && response !== "noNewShops") {
                $('#responseBody').empty();
                $('#responseBody').append(response);
                $('#nearShopsModal').modal("show")
            } else if (!newShopsOnly) {
                $('#responseBody').empty();
                $('#responseBody').append(response);
                $('#nearShopsModal').modal("show");
            }


        },
        error: function (xhr) {
            alert("There's been an error while searching for near shops");
        }
    });
}