const PORT = 8081;

let allWines = [];
 function getAllWines() {
    axios.get("http://localhost:" + PORT + "/wine/getWines")
    .then ((response) => {
        allWines = response.data;
        console.log(allWines)
        showAllWines();
    }).catch ((error) => {
        console.error(error);
    })
}

const wineList = document.getElementById("wines");

function showAllWines() {

    for (let wine of allWines) {
    
        const wineCard = document.createElement("div");
        wineCard.className = "card m-5";
    
        const cardBody = document.createElement("div");
        cardBody.className = "card-body";
        wineCard.appendChild(cardBody);
    
        const wineName = document.createElement("h5");
        wineName.className = "card-title";
        wineName.innerText = wine.name;
        cardBody.appendChild(wineName);
    
        const wineDetails = document.createElement("p");
        wineDetails.className = "card-details"
        wineDetails.innerText = "Name \n" + wine.name + "\n Grape \n" + wine.grape + "\n Description \n" + wine.description
        + "\n Tasting notes" + wine.tastingNotes;
        cardBody.appendChild(wineDetails);
    
        wineList.append(wineCard);
    }
    
}

function deleteWine() {
    axios.delete("http://localhost:8080/winepairingapp/deleteWine")
    .then ((response) => {
        removeWine(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}

function likeWine() {
    axios.put("http://localhost:8080/winepairingapp/updateWine")
    .then ((response) => {
        addLike(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}
