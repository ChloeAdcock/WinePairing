const PORT = 8081;

let allWines = [];
 function getAllWines() {
    axios.get("http://localhost:" + PORT + "/wine/getWines")
    .then ((response) => {
        allWines = response.data;
        console.log("Get wines request: " + allWines)
        showAllWines();
    }).catch ((error) => {
        console.error(error);
    })
}

const wineList = document.getElementById("wines");

function showAllWines() {

    for (let wine of allWines) {
    
        const wineCard = document.createElement("div");
        wineCard.className = "card w-30 m-3";

        const deleteButton = document.createElement("button");
        deleteButton.className = "btn btn-default btn-sm far fa-trash-alt";
        deleteButton.value = wine.id;
        deleteButton.addEventListener("click", deleteWine());
        wineCard.appendChild(deleteButton);
    
        const cardBody = document.createElement("div");
        cardBody.className = "card-body";
        wineCard.appendChild(cardBody);

        const likeButton = document.createElement("button");
        likeButton.className = "btn btn-default btn-sm far fa-thumbs-up";
        wineCard.appendChild(likeButton);
    
        const wineName = document.createElement("h5");
        wineName.className = "card-title";
        wineName.innerText = (wine.name).toUpperCase();
        cardBody.appendChild(wineName);
    
        const wineDetails = document.createElement("p");
        (wine.grape).className = "card-details";
        (wine.description).className = "card-details";
        (wine.tastingNotes).className = "card-details";
        let grape = "Grape \n";
        let description = "\n Description \n";
        let tastingNotes = "\n Tasting Notes \n";
        grape.className = "cardVariables";
        description.className = "cardVariables";
        tastingNotes.className = "cardVariables";
        wineDetails.innerText = grape + wine.grape + description + wine.description
        + tastingNotes + wine.tastingNotes;
        cardBody.appendChild(wineDetails);
       
        wineList.append(wineCard);
    }
    
}

function deleteWine() {
    axios.delete("http://localhost:" + PORT + "/wine/deleteWine/{" + deleteButton.value + "}")
    .then ((response) => {
        removeWine("Delete wine with ID of " + deleteButton.value + ": " + response.data);
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
