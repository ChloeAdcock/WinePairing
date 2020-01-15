const PORT = 8081;

let allFoods = [];
function getAllFoods() {
    axios.get("http://localhost:" + PORT + "/food/getFoods")
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
    
        const foodDetails = document.createElement("p");
        foodDetails.className = "card-details"
        let name = food.wine ?  food.wine.name :  'None';
        foodDetails.innerText = "Allergens \n" + food.allergens + "\n Description \n" + food.description
        + "\n Wine pairing \n" + name;
        cardBody.appendChild(foodDetails);

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
    axios.delete("http://localhost:" + PORT + "/food/deleteFood/" + id)
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

    axios.put("http://localhost:" + PORT + "/food/updateFood?id=" + id, data)
    .then ((response) => {
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}

