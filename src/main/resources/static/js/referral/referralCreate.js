
/**
 * オートコンプリート機能
 *
 * @param {Object} config 設定情報
 */

//Thymeleafから検索APIのURLを取得する
const configElement =
    document.getElementById("referralSearchConfig");

//患者検索APIのURL
const patientUrl = configElement.dataset.patientUrl;

//病院検索APIのURL
const hospitalUrl = configElement.dataset.hospitalUrl;

//検索を行う関数
//引数configは下部setupAutocompleteやetupAutocompleteを指す
function setupAutocomplete(config) {

	//HTMLの該当IDで要素取得
	//config.inputIdで、渡された引数のオブジェクトのinputIdが指定される
    const input = document.getElementById(config.inputId);
    const hidden = document.getElementById(config.hiddenId);
    const suggestions = document.getElementById(config.suggestionsId);

    //入力されたときに実行する関数
    input.addEventListener("input", async function () {
		
		//現在hiddenに保存されているIDを空にする
        hidden.value = "";
        //検索候補を削除する
        suggestions.innerHTML = "";
		
		//入力された文字を取得する
        const keyword = input.value.trim();

        // 空欄の場合は処理を終了
        if (keyword === "") {
            return;
        }
	
		//エラーハンドリング
        try {

            //検索APIを呼び出す(コントローラーへ)
            //encodeURIComponent:日本語や空白などの文字をURLで安全に扱えるようにする
            const response = await fetch(
                `${config.url}?keyword=${encodeURIComponent(keyword)}`
            );
			
			//respons.okでHTTPリクエストの成功を確認。！で否定
			//失敗時エラー処理実行
            if (!response.ok) {
                throw new Error("検索に失敗しました");
            }
			
			//controllerから返されたJSON形式のデータをjsで扱えるようにする
            const results = await response.json();

            //配列のデータをひとつづつ取り出し検索結果を表示
            results.forEach(item => {
				
				//HTML要素を作成
                const suggestion = document.createElement("div");
				
				//作成した要素にクラスを追加
                suggestion.classList.add("suggestion-item");

                // 表示する文字を設定。display: item => item.nameに対応
                suggestion.textContent = config.display(item);

                // 候補をクリックした場合
                suggestion.addEventListener("click", function () {

					//入力欄に名前を設定
                    input.value = config.display(item);
                    //hiddenに引数のオブジェクトのidFieldを設定
                    hidden.value = item[config.idField];

                    //候補一覧を消す
                    suggestions.innerHTML = "";
                });
				
				//作成したHTML要素を候補表示欄の中に追加する
                suggestions.appendChild(suggestion);
            });

        } catch (error) {
            console.error(error);
        }
    });
}


//検索部分を共通の部品とし、異なる部分だけ設定として渡す
// 患者の場合
setupAutocomplete({
    inputId: "patientName",
    hiddenId: "patientId",
    suggestionsId: "patientSuggestions",
    url: patientUrl,
    idField: "patientId",
    display: item => item.name
});


// 宛先病院の場合
setupAutocomplete({
    inputId: "recipientHospitalName",
    hiddenId: "recipientHospitalId",
    suggestionsId: "hospitalSuggestions",
    url: hospitalUrl,
    idField: "hospitalId",
    display: item => item.hospitalName
});
