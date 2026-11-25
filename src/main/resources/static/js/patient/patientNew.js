/**
 * 
 */


// アレルギー入力欄の件数
let allergyCount = 1;

// HTML読み込み後に実行
document.addEventListener("DOMContentLoaded", function () {

    const allergyYes = document.getElementById("allergyYes");
    const allergyNo = document.getElementById("allergyNo");
    const allergyContainer = document.getElementById("allergyContainer");
    const addAllergyButton = document.getElementById("addAllergyButton");

    // アレルギーあり・なしの切り替え
    function changeAllergyDisplay() {

        if (allergyYes.checked) {
            allergyContainer.style.display = "block";
            addAllergyButton.style.display = "inline-block";

        } else {
            allergyContainer.style.display = "none";
            addAllergyButton.style.display = "none";
        }
    }

    allergyYes.addEventListener("change", changeAllergyDisplay);
    allergyNo.addEventListener("change", changeAllergyDisplay);

});

// アレルギー入力欄の追加
function addAllergy() {

    const container = document.getElementById("allergyContainer");

    const newRow = document.createElement("div");
    newRow.className = "allergy-row";

    newRow.innerHTML = `
        <p>
            アレルギー種別：
            <input type="text"
                   name="allergies[${allergyCount}].allergyType">
        </p>

        <p>
            アレルギー症状：
            <input type="text"
                   name="allergies[${allergyCount}].allergySymptom">
        </p>
    `;

    container.appendChild(newRow);

    allergyCount++;
}

