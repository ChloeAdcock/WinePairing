function addFood() {
    axios.get("http://localhost:8080/winepairingapp/addFood")
    .then ((response) => {
        newFood(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}

function addWine() {
    axios.get("http://localhost:8080/winepairingapp/addWine")
    .then ((response) => {
        newWine(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}