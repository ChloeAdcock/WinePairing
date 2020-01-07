function getWines() {
    axios.get("http://localhost:8080/winepairingapp/getWines")
    .then ((response) => {
        showWines(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}

function deleteWine() {
    axios.get("http://localhost:8080/winepairingapp/deleteWine")
    .then ((response) => {
        removeWine(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}

function likeWine() {
    axios.get("http://localhost:8080/winepairingapp/updateWine")
    .then ((response) => {
        addLike(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}
