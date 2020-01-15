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

function searchFoods() {

    for (let food of allFoods) {
    
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
        
        foodList.append(foodCard);
    }
}

