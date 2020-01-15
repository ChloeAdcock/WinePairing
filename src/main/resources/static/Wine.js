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

        let deleteWineButton = document.createElement("button");
        deleteWineButton.className = "btn btn-default btn-sm far fa-trash-alt";
        deleteWineButton.id = wine.id;
        wineCard.appendChild(deleteWineButton);
        deleteWineButton.addEventListener('click', () => deleteWine(wine.id));
    
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

        const numLikes = document.createElement("p");
        numLikes.className = "numLikes";
        numLikes.innerText = wine.likes;
        wineCard.appendChild(numLikes);

        let likeWineButton = document.createElement("button");
        likeWineButton.className = "btn btn-default btn-sm far fa-thumbs-up";
        likeWineButton.id = wine.id;
        wineCard.appendChild(likeWineButton);
        likeWineButton.addEventListener('click', () => updateWineLikes(wine, wine.id));
       
        wineList.append(wineCard);
    }
    
}

function deleteWine(id) {
    axios.delete("http://localhost:" + PORT + "/wine/deleteWine/" + id )
    .then ((response) => {
        console.log("Delete wine with ID of " + id + ": " + response.data);
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}

function updateWineLikes(wineObj, id) {

    let data = {
        "name": wineObj.name,
        "grape": wineObj.grape,
        "description": wineObj.description,
        "tastingNotes": wineObj.tastingNotes,
        "likes": wineObj.likes
    }

    console.log(id);
    console.log("Like wine: " + JSON.stringify(data));

    axios.put("http://localhost:" + PORT + "/wine/updateWine?id=" + id, data)
    .then ((response) => {
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}
