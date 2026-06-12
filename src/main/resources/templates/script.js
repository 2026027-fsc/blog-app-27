var req = null; // XMLHttpRequestオブジェクト変数の宣言
function getnavi(){
    req = new XMLHttpRequest(); // XMLHttpRequestオブジェクトの生成
    req.onload = shownavi; // リクエスト終了後にロードされる関数を設定
    req.open("GET", "./navi.html"); // リクエストURLを設定
    req.send(); // リクエストを実行

}
function shownavi(){
    ret = req.responseText; // 返却されたオブジェクトから、テキストで情報取得
    navi_obj = document.getElementById("v_navi") // ナビゲーションを入れたい場所(今回の場合は、<span id="v_navi">)のオブジェクトを取得
    navi_obj.innerHTML = ret // ナビゲーション情報を設定
}