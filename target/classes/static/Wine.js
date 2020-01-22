let allWines = [];
 function getAllWines() {
    axios.get("/WinePairing/wine/getWines/")
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
    
        const wineDetails = document.createElement("div");
        cardBody.appendChild(wineDetails);

        let grapeP = document.createElement("p");
        grapeP.innerText = "Grape"
        grapeP.id = "variableName";
        wineDetails.appendChild(grapeP);

        let grape = document.createElement("p");
        grape.innerText = wine.grape;
        grape.className = "card-details"
        wineDetails.appendChild(grape);

        let descriptionP = document.createElement("p");
        descriptionP.innerText = "Description";
        descriptionP.id = "variableName";
        wineDetails.appendChild(descriptionP);

        let description = document.createElement("p");
        description.innerText = wine.description;
        descriptionP.className = "card-details"
        wineDetails.appendChild(description);

        let notesP = document.createElement("p");
        notesP.innerText = "Tasting notes";
        notesP.id = "variableName";
        wineDetails.appendChild(notesP);

        let notes = document.createElement("p");
        notes.innerText = wine.tastingNotes;
        notes.className = "card-details";
        wineDetails.appendChild(notes);

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
    axios.delete("/WinePairing/wine/deleteWine/" + id )
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

    axios.put("/WinePairing/wine/updateWine?id=" + id, data)
    .then ((response) => {
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}
