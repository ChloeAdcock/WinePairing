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
const deleteWineButton = document.createElement("button");

function showAllWines() {

    for (let wine of allWines) {
    
        const wineCard = document.createElement("div");
        wineCard.className = "card w-30 m-3";

        deleteWineButton.className = "btn btn-default btn-sm far fa-trash-alt";
        deleteWineButton.id = wine.id;
        wineCard.appendChild(deleteWineButton);
        deleteWineButton.onclick = deleteWine;
    
        const cardBody = document.createElement("div");
        cardBody.className = "card-body";
        wineCard.appendChild(cardBody);
    
        const wineName = document.createElement("h5");
        wineName.className = "card-title";
        wineName.innerText = (wine.name).toUpperCase();
        cardBody.appendChild(wineName);
    
        const wineDetails = document.createElement("p");
        wineDetails.className = "card-details";
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
    axios.delete("http://localhost:" + PORT + "/wine/deleteWine/" + deleteWineButton.id )
    .then ((response) => {
        console.log("Delete wine with ID of " + deleteWineButton.id + ": " + response.data);
    }).catch ((error) => {
        console.error(error);
    })
}

function likeWine() {

    let wineLikes = document.getElementById(wine.id).value;


    axios.put("http://localhost:" + PORT + "/wine/updateWine?id=" + likeWineButton.id)
    .then ((response) => {
        addLike(response.data);
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}
