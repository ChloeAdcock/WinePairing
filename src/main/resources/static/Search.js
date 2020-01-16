let allWines = [];
 
function getAllWines() {
    axios.get("/WinePairing/wine/getWines")
    .then ((response) => {
        allWines = response.data;
        console.log("Get all wines request: " + allWines)
    }).catch ((error) => {
        console.error(error);
    })
}

let allFoods = [];

function getAllFoods() {
    axios.get("/WinePairing/food/getFoods")
    .then ((response) => {
        allFoods = response.data;
        console.log("Get all foods request: " + allFoods);
    }).catch ((error) => {
        console.error(error);
    })
}

const itemsList = document.getElementById("items");

function searchFoods() {

    for (let food of allFoods) {

        let searchInput = document.getElementById("search_input").value;
        if (Object.values(food).map(f => {
            return ("" + f).toLowerCase();
        }).indexOf(searchInput.toLowerCase()) != -1) {
    
        const foodCard = document.createElement("div");
        foodCard.className = "card w-30 m-3";
    
        const cardBody = document.createElement("div");
        cardBody.className = "card-body";
        foodCard.appendChild(cardBody);
    
        const foodName = document.createElement("h5");
        foodName.className = "card-title";
        foodName.innerText = (food.name).toUpperCase();
        cardBody.appendChild(foodName);
    
        const foodDetails = document.createElement("div");
        cardBody.appendChild(foodDetails);

        let allergensP = document.createElement("p");
        allergensP.innerText = "Allergens"
        allergensP.id = "variableName";
        foodDetails.appendChild(allergensP);

        let allergens = document.createElement("p");
        allergens.innerText = food.allergens;
        allergens.className = "card-details";
        foodDetails.appendChild(allergens);

        let descriptionP = document.createElement("p");
        descriptionP.innerText = "Description";
        descriptionP.id = "variableName";
        foodDetails.appendChild(descriptionP);

        let description = document.createElement("p");
        description.innerText = food.description;
        description.className = "card-details";
        foodDetails.appendChild(description);

        let wineP = document.createElement("p");
        wineP.innerText = "Wine pairing";
        wineP.id = "variableName";
        foodDetails.appendChild(wineP);

        let wine = document.createElement("p");
        let name = food.wine ? food.wine.name : "None";
        wine.innerText = name;
        wine.className = "card-details";
        foodDetails.appendChild(wine);

        itemsList.append(foodCard);

        }
    }
}

function searchWines() {

    for (let wine of allWines) {

        let searchInput = document.getElementById("search_input").value;
    
        if (Object.values(wine).map(f => {
            return ("" + f).toLowerCase();
        }).indexOf(searchInput.toLowerCase()) != -1) {

        const wineCard = document.createElement("div");
        wineCard.className = "card w-30 m-3";
    
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
       
        itemsList.append(wineCard);
    }
}
}
function clearSearches() {
    itemsList.innerHTML = "";
}




