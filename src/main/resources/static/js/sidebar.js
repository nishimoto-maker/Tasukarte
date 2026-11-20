
// サイドバーを取得
const sidebar = document.querySelector(".sidebar");

// ハンバーガーボタンを取得
const sidebarToggle = document.querySelector(".sidebar-toggle");

// 大機能のメニューボタンをすべて取得
const menuParents = document.querySelectorAll(".menu-parent");


// ① サイドバー全体の開閉処理
sidebarToggle.addEventListener("click", function() {

    // collapsedクラスを付け外しする
    sidebar.classList.toggle("collapsed");

});


// ② 大機能メニューの展開・収納処理
menuParents.forEach(function(menuParent) {

    // 大機能のボタンがクリックされたとき
    menuParent.addEventListener("click", function() {

        // サイドバー収納中は操作しない
        if (sidebar.classList.contains("collapsed")) {
            return;
        }

        // クリックされたボタンの親要素を取得
        const menuGroup = menuParent.closest(".menu-group");

        // activeクラスを付け外しする
        menuGroup.classList.toggle("active");

    });

});
