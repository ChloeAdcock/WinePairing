const PORT = 8081;

let allWines = [];
 
function getAllWines() {
    axios.get("http://localhost:" + PORT + "/wine/getWines")
    .then ((response) => {
        allWines = response.data;
        console.log("Get all wines request: " + allWines)
    }).catch ((error) => {
        console.error(error);
    })
}

let allFoods = [];

function getAllFoods() {
    axios.get("http://localhost:" + PORT + "/food/getFoods")
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
        debugger;
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
    
        const foodDetails = document.createElement("p");
        foodDetails.className = "card-details"
        let name = food.wine ?  food.wine.name :  'None';
        foodDetails.innerText = "Allergens \n" + food.allergens + "\n Description \n" + food.description
        + "\n Wine pairing \n" + name;
        cardBody.appendChild(foodDetails);

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
    
        const wineDetails = document.createElement("p");
        wineDetails.className = "card-details";
        let grape = "Grape \n";
        let description = "\n Description \n";
        let tastingNotes = "\n Tasting Notes \n";
        wineDetails.innerText = grape + wine.grape + description + wine.description
        + tastingNotes + wine.tastingNotes;
        cardBody.appendChild(wineDetails);
       
        itemsList.append(wineCard);
    }
}
}
function clearSearches() {
    itemsList.innerHTML = "";
}




