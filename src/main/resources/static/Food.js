let allFoods = [];
function getAllFoods() {
    axios.get("/WinePairing/food/getFoods")
    .then ((response) => {
        allFoods = response.data;
        console.log("Get all foods request: " + allFoods);
        showAllFoods();
    }).catch ((error) => {
        console.error(error);
    })
}

const foodList = document.getElementById("foods");

function showAllFoods() {

    for (let food of allFoods) {
    
        const foodCard = document.createElement("div");
        foodCard.className = "card w-30 m-3";

        let deleteFoodButton = document.createElement("button");
        deleteFoodButton.className = "btn btn-default btn-sm far fa-trash-alt";
        deleteFoodButton.id = food.id;
        foodCard.appendChild(deleteFoodButton);
        deleteFoodButton.addEventListener('click', () => deleteFood(food.id));
    
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

        const numLikes = document.createElement("p");
        numLikes.className = "numLikes";
        numLikes.innerText = food.likes;
        foodCard.appendChild(numLikes);

        let likeFoodButton = document.createElement("button");
        likeFoodButton.className = "btn btn-default btn-sm far fa-thumbs-up";
        likeFoodButton.id = food.id;
        foodCard.appendChild(likeFoodButton);
        likeFoodButton.addEventListener('click', () => updateFoodLikes(food, food.id));
    
        foodList.append(foodCard);
    }
    
}

function deleteFood(id) {
    axios.delete("/WinePairing/food/deleteFood/" + id)
    .then ((response) => {
        console.log("Delete food with ID of " + id + ": " + response.data);
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}


function updateFoodLikes(foodObj, id) {

    let data = {
        "name": foodObj.name,
        "allergens": foodObj.allergens,
        "description": foodObj.description,
        "likes": foodObj.likes,
        "wine": {
            "id": foodObj.wine.id
        }
    }

    console.log(id);
    console.log("Like food: " + JSON.stringify(data));

    axios.put("/WinePairing/food/updateFood?id=" + id, data)
    .then ((response) => {
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}

